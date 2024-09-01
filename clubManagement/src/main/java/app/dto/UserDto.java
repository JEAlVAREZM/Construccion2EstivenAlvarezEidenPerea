package app.dto;

public class UserDto {
    	private long id;
        private Long personId;
        private String password;
        private String userName;
        private String role;

        public UserDto() {
        }
        
        public long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Long getPersonId() {
            return personId;
        }

        public void setPersonId(long personId) {
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
