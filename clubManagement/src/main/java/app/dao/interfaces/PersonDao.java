package app.dao.interfaces;

import app.model.Person;
import java.sql.SQLException;

public interface PersonDao {
    void insertPerson(Person person) throws SQLException;
    Person getPersonById(long personId) throws SQLException;

}
