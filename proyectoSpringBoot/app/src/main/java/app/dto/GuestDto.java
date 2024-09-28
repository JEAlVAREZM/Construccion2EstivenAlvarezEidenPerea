package app.dto;



public class GuestDto {
        private long id;
        private UserDto userId;
        private PartnerDto partnerId;
        private boolean stateInvitation;
        public GuestDto() {
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

        public PartnerDto getPartnerId() {
            return partnerId;
        }

        public void setPartnerId(PartnerDto partnerId) {
            this.partnerId = partnerId;
        }

        public boolean isStateInvitation() {
            return stateInvitation;
        }

        public void setStateInvitation(boolean stateInvitation) {
            this.stateInvitation = stateInvitation;
        }
	
}
