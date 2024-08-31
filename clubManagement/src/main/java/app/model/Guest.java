package app.model;

public class Guest {
        private long id;
        private User userId;
        private Partner partner;
        private boolean stateInvitation;

        public Guest() {
        }

        public Guest(long id, User userId, Partner partner, boolean stateInvitation) {
            this.id = id;
            this.userId = userId;
            this.partner = partner;
            this.stateInvitation = stateInvitation;
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

        public Partner getPartner() {
            return partner;
        }

        public void setPartner(Partner partner) {
            this.partner = partner;
        }

        public boolean isStateInvitation() {
            return stateInvitation;
        }

        public void setStateInvitation(boolean stateInvitation) {
            this.stateInvitation = stateInvitation;
        }

}
