package app.dao;

import app.dao.interfaces.InvoiceDao;
import app.dto.InvoiceDto;
import app.model.Invoice;
import app.model.Person;
import app.model.Partner;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDaoImplementation implements InvoiceDao {
    private Connection connection;
    public InvoiceDaoImplementation(Connection connection) {
        this.connection = connection;
    }

    public InvoiceDaoImplementation() {

    }

    @Override
    public void createInvoice(Invoice invoice) throws SQLException {
        String sql = "INSERT INTO invoice (PERSONID, PARTNERID, CREATIONDATE, AMOUNT, STATUS) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, invoice.getPersonId().getId());
            pstmt.setLong(2, invoice.getPartnerId().getId());
            pstmt.setDate(3, invoice.getDate());
            pstmt.setDouble(4, invoice.getAmount());
            pstmt.setBoolean(5, invoice.getStateInvoice());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Invoice findById(long id) throws SQLException {
        String sql = "SELECT * FROM invoice WHERE ID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToInvoice(rs);
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public List<Invoice> findPendingInvoicesByPartnerId(long partnerId) throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoice WHERE PARTNERID = ? AND STATUS = 'PENDING'";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, partnerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    invoices.add(mapResultSetToInvoice(rs));
                }
            }
        }
        return invoices;
    }

    @Override
    public void updateInvoice(InvoiceDto invoice) throws SQLException {
        String sql = "UPDATE invoice SET PERSONID = ?, PARTNERID = ?, CREATIONDATE = ?, AMOUNT = ?, STATUS = ? WHERE ID = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setLong(1, invoice.getPersonId().getId());
            pstmt.setLong(2, invoice.getPartnerId().getId());
            pstmt.setDate(3, invoice.getDate());
            pstmt.setDouble(4, invoice.getAmount());
            pstmt.setBoolean(5, invoice.getStateInvoice());
            pstmt.setLong(6, invoice.getId());
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<Invoice> getAllInvoices() throws SQLException {
        List<Invoice> invoices = new ArrayList<>();
        String sql = "SELECT * FROM invoice";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                invoices.add(mapResultSetToInvoice(rs));
            }
        }
        return invoices;
    }

    private Invoice mapResultSetToInvoice(ResultSet rs) throws SQLException {
        long id = rs.getLong("ID");
        Person person = new Person(rs.getLong("PERSONID"));
        Partner partner = new Partner(rs.getLong("PARTNERID"));
        Date creationDate = rs.getDate("CREATIONDATE");
        double amount = rs.getDouble("AMOUNT");
        boolean status = Boolean.parseBoolean(rs.getString("STATUS"));

        return new Invoice(id, person, partner, creationDate, amount, status);
    }

    @Override
    public boolean hasPendingInvoices(long guestId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM invoices WHERE guestId = ? AND status = 'PENDING'";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, guestId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
        return false;
    }
}