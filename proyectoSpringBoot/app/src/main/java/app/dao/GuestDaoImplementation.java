package app.dao;
import app.dao.interfaces.GuestDao;
import app.dao.interfaces.InvoiceDao;
import app.model.Guest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GuestDaoImplementation implements GuestDao {
    private Connection connection;

    public GuestDaoImplementation(Connection connection) {
        this.connection = connection;
    }

    // Metodo para verificar el número de invitados activos para un socio regular
    public boolean canAddGuest(long partnerId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM guest WHERE partnerId = ? AND status = true";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, partnerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count < 3; // Limite de 3 invitados activos para socios regulares
            }
        }
        return false;
    }

    @Override
    public void addGuest(Guest guest) throws SQLException {

    }

    @Override
    public Guest getGuestById(long id) throws SQLException {
        return null;
    }

    @Override
    public List<Guest> getAllGuests() throws SQLException {
        return List.of();
    }

    @Override
    public void updateGuest(Guest guest) throws SQLException {

    }

    @Override
    public void deleteGuest(long id) throws SQLException {

    }

    // Metodo para marcar una invitación como inactiva
    @Override
    public void deactivateGuest(long guestId) throws SQLException {
        String sql = "UPDATE guest SET status = false WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, guestId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean hasPendingInvoices(long guestId) throws SQLException {

        InvoiceDao invoiceDao = new InvoiceDaoImplementation(connection);
        return invoiceDao.hasPendingInvoices(guestId);
    }

    public boolean convertGuestToPartner(long guestId) throws SQLException {
        if (hasPendingInvoices(guestId)) {
            return false;
        }
        deactivateGuest(guestId);
        return true;
    }
}
