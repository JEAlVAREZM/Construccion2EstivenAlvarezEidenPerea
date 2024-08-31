package app.dto;

import java.sql.Date;

public class InvoiceDto {
    private long id;
    private PersonDto personId;
    private PartnerDto partnerId;
    private Date date;
    private double amount;
    private boolean stateInvoice;

    public InvoiceDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PersonDto getPersonId() {
        return personId;
    }

    public void setPersonId(PersonDto personId) {
        this.personId = personId;
    }

    public PartnerDto getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(PartnerDto partnerId) {
        this.partnerId = partnerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean getStateInvoice() {
        return stateInvoice;
    }

    public void setStateInvoice(boolean stateInvoice) {
        this.stateInvoice = stateInvoice;
    }

}