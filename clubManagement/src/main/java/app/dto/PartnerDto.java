
package app.dto;
import java.sql.Date;
    
public class PartnerDto {
        private long id;
        private UserDto userId;
        private double availableMoney;
        private String subscriptionType;
        private Date affiliationDate;

        public PartnerDto() {
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public UserDto getUserId() {
            return userId;
        }

        public void setUserId(UserDto userId) {
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

        public java.sql.Date getAffiliationDate() {return affiliationDate;}

        public void setAffiliationDate(java.sql.Date affiliationDate) {this.affiliationDate = affiliationDate;}
        

}
