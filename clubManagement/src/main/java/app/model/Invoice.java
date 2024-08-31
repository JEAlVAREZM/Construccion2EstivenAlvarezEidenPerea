package app.model;
import java.sql.Date;

public class Invoice {
    	private long id;
        private Person personId;
        private Partner partnerId;
        private Date date;
        private double amount;
        private boolean stateInvoice;

        public Invoice() {
        }

        public Invoice(Long id, Person personId, Partner partnerId, Date date, double amount, boolean stateInvoice) {
            this.id = id;
            this.personId = personId;
            this.partnerId = partnerId;
            this.date = date;
            this.amount = amount;
            this.stateInvoice = stateInvoice;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Person getPersonId() {
            return personId;
        }

        public void setPersonId(Person personId) {
            this.personId = personId;
        }

        public Partner getPartnerId() {
            return partnerId;
        }

        public void setPartnerId(Partner partnerId) {
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

        public boolean getStateInvoice() {return stateInvoice;}

        public void setStateInvoice(boolean stateInvoice) {this.stateInvoice = stateInvoice;}


}
