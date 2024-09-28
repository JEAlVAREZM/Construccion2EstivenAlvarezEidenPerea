package app.service.interfaces;

import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.UserDto;
import app.model.Invoice;
import java.sql.SQLException;
import java.util.List;

public interface AdminService {
    void registerPartner(PartnerDto partnerDto) throws Exception;
    void viewInvoiceHistory() throws Exception;
    void promoteToVIP(UserDto userDto) throws Exception;
    void createUser(UserDto userDto) throws Exception;
    List<InvoiceDto> getClubInvoiceHistory() throws Exception;
    List<InvoiceDto> getMemberInvoiceHistory(long partnerId) throws Exception;
    List<InvoiceDto> getGuestInvoiceHistory(long guestId) throws Exception;
    List<Invoice> getAllInvoices() throws SQLException;
    Invoice getInvoiceById(long invoiceId) throws SQLException;
}
