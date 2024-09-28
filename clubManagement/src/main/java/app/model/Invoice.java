package app.model;

import java.util.Date;

public class Invoice {

    private long id;
    private Person PersonId;
    private Partner partnerId;
    private Date creaciondate;
    private double amount;
    private boolean status;

    public Invoice() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPersonId() {
        return PersonId;
    }

    public void setPersonId(Person PersonId) {
        this.PersonId = PersonId;
    }

    public Partner getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Partner partnerId) {
        this.partnerId = partnerId;
    }

    public Date getCreaciondate() {
        return creaciondate;
    }

    public void setCreaciondate(Date CreacionDate) {
        this.creaciondate = CreacionDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
