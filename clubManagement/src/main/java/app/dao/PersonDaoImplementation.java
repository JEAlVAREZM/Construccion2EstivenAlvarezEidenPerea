package app.dao;

import app.dao.interfaces.PersonDao;
import app.dto.PersonDto;
import app.model.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDaoImplementation implements PersonDao {
    private Connection connection;

    public PersonDaoImplementation(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insertPerson(Person person) throws SQLException {
        String sql = "INSERT INTO person (name, document, phone) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, person.getName());
            statement.setLong(2, person.getDocument());
            statement.setLong(3, person.getPhone());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                person.setId(generatedKeys.getLong(1));
            }
        }
    }

    @Override
    public Person getPersonById(long personId) throws SQLException {
        String sql = "SELECT * FROM person WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, personId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToPerson(resultSet);
            }
        }
        return null;
    }

    private Person mapResultSetToPerson(ResultSet resultSet) throws SQLException {
        Person person = new Person();
        person.setId(resultSet.getLong("ID"));
        person.setName(resultSet.getString("NAME"));
        person.setDocument(Long.parseLong(resultSet.getString("DOCUMENT")));
        person.setPhone(Long.parseLong(resultSet.getString("PHONE")));
        return person;
    }

    @Override
    public void createPerson(PersonDto personDto) throws SQLException {
        String sql = "INSERT INTO person (document, name, cellphone) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, personDto.getDocument());
            statement.setString(2, personDto.getName());
            statement.setLong(3, personDto.getPhone());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    personDto.setId(generatedKeys.getLong(1));
                }
            }
        }
    }
}
