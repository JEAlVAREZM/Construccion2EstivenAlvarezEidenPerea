
package app.dto;
import java.sql.Date;
    
public class PartnerDto {
        private long id;
        private Long userId;
        private double availableMoney;
        private String subscriptionType;
        private java.sql.Timestamp affiliationDate;

        public PartnerDto() {
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
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

        public java.sql.Timestamp getAffiliationDate() {return affiliationDate;}

        public void setAffiliationDate(java.sql.Timestamp affiliationDate) {this.affiliationDate = affiliationDate;}
        

}
