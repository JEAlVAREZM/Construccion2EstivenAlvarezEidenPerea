package app.dao.interfaces;
import java.sql.SQLException;
import java.util.List;
import app.model.Guest;


public interface GuestDao {
    public void addGuest(Guest guest) throws SQLException;
    public Guest getGuestById(long id) throws SQLException;
    public List<Guest> getAllGuests() throws SQLException;
    public void updateGuest(Guest guest) throws SQLException;
    public void deleteGuest(long id) throws SQLException;
    public void deactivateGuest(long guestId) throws SQLException;
    public boolean hasPendingInvoices(long guestId) throws SQLException;
    public boolean convertGuestToPartner(long guestId) throws SQLException;
}
