package app.dao;

import app.config.MysqlConnection;
import app.dao.interfaces.AdminDao;
import app.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AdminDaoImplementation implements AdminDao {
    private Connection getConnection() {
        return MysqlConnection.getConnection();
    }

    public void createUser(UserDto userDto) throws Exception {
        String sql = "INSERT INTO user (personnid, username, password, role) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, userDto.getPersonId());
            preparedStatement.setString(2, userDto.getUserName());
            preparedStatement.setString(3, userDto.getPassword());
            preparedStatement.setString(4, userDto.getRole());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al crear el usuario", e);
        }
    }

    @Override
    public void deleteUser(Long userId) throws Exception {
        String sql = "DELETE FROM user WHERE ID = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar el usuario", e);
        }
    }
}
