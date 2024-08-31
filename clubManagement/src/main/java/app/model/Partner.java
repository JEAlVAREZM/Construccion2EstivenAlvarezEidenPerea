package app.model;
import java.sql.Date;
    
public class Partner {
        private long id;
        private User userId;
        private double availableMoney;
        private String subscriptionType;
        private Date affiliationDate;

        public Partner(long id, User userId, double availableMoney, String subscriptionType, Date affiliationDate) {
            this.id = id;
            this.userId = userId;
            this.availableMoney = availableMoney;
            this.subscriptionType = subscriptionType;
            this.affiliationDate = affiliationDate;
        }

    public Partner(long partnerid) {
    }

    public Partner() {

    }

    public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public User getUserId() {
            return userId;
        }

        public void setUserId(User userId) {
            this.userId = userId;
        }

        public double getAvailableMoney() {
            return availableMoney;
        }

        public void setAvailableMoney(double availableMoney) {
            this.availableMoney = availableMoney;
        }

        public String getSubscriptionType() {
            return subscriptionType;
        }

        public void setSubscriptionType(String subscriptionType) {
            this.subscriptionType = subscriptionType;
        }

        public Date getAfiliationDate() {
            return affiliationDate;
        }

        public void setAffiliationDate(Date afiliationDate) {
            this.affiliationDate = afiliationDate;
        }

        public void setActive(boolean b) {
        }
}
