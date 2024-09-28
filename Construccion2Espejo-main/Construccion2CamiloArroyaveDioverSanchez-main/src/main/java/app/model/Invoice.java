/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.model;

import java.util.Date;

/**
 *
 * @author Camilo
 */
public class Invoice {

    private long id;
    private Person PersonId;
    private Partner partnerId;
    private Date CreacionDate;
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

    public Date getCreacionDate() {
        return CreacionDate;
    }

    public void setCreacionDate(Date CreacionDate) {
        this.CreacionDate = CreacionDate;
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
