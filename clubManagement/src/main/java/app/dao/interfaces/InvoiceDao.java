package app.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import app.dto.InvoiceDto;
import app.model.Invoice;

public interface InvoiceDao {
    void createInvoice(Invoice invoice) throws Exception;
    Invoice findById(long id) throws Exception;
    List<Invoice> findPendingInvoicesByPartnerId(long partnerId) throws Exception;
    void updateInvoice(InvoiceDto invoice) throws Exception;
    List<Invoice> getAllInvoices() throws SQLException;
    boolean hasPendingInvoices(long guestId) throws SQLException;

}