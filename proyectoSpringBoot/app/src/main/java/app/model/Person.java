
package app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="person")
public class Person {
    @Id
    	private long id;
        private String name;
        private long document;
        private long phone;
        

        public Person(long id, String name, long document, long phone) {
            this.id = id;
            this.name = name;
            this.document = document;
            this.phone = phone;
        }

    public Person(long personid) {
    }

    public Person() {

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
