package app.model;

public class User {
    	private long id;
        private Person personId;
        private String password;
        private String userName;
        private String role;

        public User(long id, Person personId, String password, String userName, String role) {
            this.id = id;
            this.personId = personId;
            this.password = password;
            this.userName = userName;
            this.role = role;
        }

    public User() {

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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

}
