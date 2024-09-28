package app.service.interfaces;

import app.dao.interfaces.PartnerDao;
import app.dao.interfaces.InvoiceDao;
import app.dao.interfaces.GuestDao;
import app.model.Invoice;
import app.model.Partner;
import app.dto.InvoiceDto;
import app.model.Guest;
import java.sql.SQLException;
import java.util.List;

public class PartnerService {

    private PartnerDao partnerDao;
    private InvoiceDao invoiceDao;
    private GuestDao guestDao;

    public PartnerService(PartnerDao partnerDao, InvoiceDao invoiceDao, GuestDao guestDao) {
        this.partnerDao = partnerDao;
        this.invoiceDao = invoiceDao;
        this.guestDao = guestDao;
    }

    public void requestDeactivation(Partner partner) throws Exception {
        if (hasPendingInvoices(partner)) {
            throw new Exception("No se puede desactivar el socio porque tiene facturas pendientes.");
        }
        partner.setActive(false);
        partnerDao.updatePartner(partner);
        deactivateAllGuests(partner);
    }

    public void addFounds(Partner partner, double amount) throws Exception {
        validateFoundsIncrement(partner, amount);
        payPendingInvoices(partner);
        partner.setAvailableMoney(partner.getAvailableMoney() + amount);
        partnerDao.updatePartner(partner);
    }

    private void validateFoundsIncrement(Partner partner, double amount) throws Exception {
        if (partner.getSubscriptionType().equals("Regular") && amount > 1000000) {
            throw new Exception("El incremento máximo para socios regulares es de 1'000.000.");
        }
        if (partner.getSubscriptionType().equals("VIP") && amount > 5000000) {
            throw new Exception("El incremento máximo para socios VIP es de 5'000.000.");
        }
    }

    private boolean hasPendingInvoices(Partner partner) {
        try {
            List<Invoice> pendingInvoices = invoiceDao.findPendingInvoicesByPartnerId(partner.getId());
            return !pendingInvoices.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void payPendingInvoices(Partner partner) throws Exception {
        List<Invoice> pendingInvoices = invoiceDao.findPendingInvoicesByPartnerId(partner.getId());
        for (Invoice invoice : pendingInvoices) {
            if (partner.getAvailableMoney() >= invoice.getAmount()) {
                partner.setAvailableMoney(partner.getAvailableMoney() - invoice.getAmount());
                invoice.setStateInvoice(true);
                /*invoiceDao.updateInvoice(invoice);*/
            } else {
                break;
            }
        }
    }

    private void deactivateAllGuests(Partner partner) throws SQLException {
        List<Guest> guests = (List<Guest>) guestDao.getGuestById(partner.getId());
        for (Guest guest : guests) {
            guest.setStateInvitation(false);
            guestDao.updateGuest(guest);
        }
    }
}
