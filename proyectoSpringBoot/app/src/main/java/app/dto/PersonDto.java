
package app.dto;

public class PersonDto {
    	private long id;
        private String name;
        private long document;
        private long phone;
        
        public PersonDto() {
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getDocument() {
            return document;
        }

        public void setDocument(long document) {
            this.document = document;
        }

        public long getPhone() {
            return phone;
        }

        public void setPhone(long phone) {
            this.phone = phone;
        }

}
