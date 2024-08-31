package app.dao;

import app.config.MysqlConnection;
import app.dao.interfaces.UserDao;
import app.dto.UserDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImplementation implements UserDao {
    private Connection connection = MysqlConnection.getConnection();


    public UserDaoImplementation() {

    }

    @Override
    public void createUser(UserDto userDto) throws SQLException {
        String sql = "INSERT INTO user (ID, PERSONID, username, password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, userDto.getId());
            statement.setString(2, userDto.getUserName());
            statement.setString(3, userDto.getPassword());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                userDto.setId(generatedKeys.getLong(1));
            }
        }
    }

    @Override
    public UserDto findById(long id) throws SQLException {
        String sql = "SELECT * FROM user WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToUserDto(resultSet);
            }
        }
        return null;
    }

    @Override
    public UserDto findByUserName(String username) throws SQLException {
        String sql = "SELECT * FROM user WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToUserDto(resultSet);
            }
        }
        return null;
    }

    @Override
    public boolean existsByUserName(String username) throws SQLException {
        String sql = "SELECT COUNT(*) FROM user WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        }
        return false;
    }

    private UserDto mapResultSetToUserDto(ResultSet resultSet) throws SQLException {
        UserDto userDto = new UserDto();
        userDto.setId(resultSet.getLong("ID"));
        userDto.setPersonId(resultSet.getLong("PERSONNID"));
        userDto.setUserName(resultSet.getString("USERNAME"));
        userDto.setPassword(resultSet.getString("PASSWORD"));
        userDto.setRole(resultSet.getString("ROLE"));
        return userDto;
    }
}
