package app.dao;

import app.config.MysqlConnection;
import app.dao.interfaces.UserDao;
import app.dto.UserDto;
import app.helper.Helper;
import app.model.Person;
import app.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDaoImplementation implements UserDao {

    @Override

    public UserDto findByUserName(UserDto userDto) throws Exception {
        String query = "SELECT ID,USERNAME,PASSWORD,ROLE ,PERSONNID FROM USER WHERE USERNAME = ?";
        PreparedStatement preparedStatement = MysqlConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, userDto.getUserName());
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            User user = new User();
            user.setId(resulSet.getLong("ID"));
            user.setUserName(resulSet.getString("USERNAME"));
            user.setPassword(resulSet.getString("PASSWORD"));
            user.setRole(resulSet.getString("ROLE"));
            Person person = new Person();
            person.setId(resulSet.getLong("PERSONNID"));
            user.setPersonId(person);
            resulSet.close();
            preparedStatement.close();
            return Helper.parse(user);
        }
        resulSet.close();
        preparedStatement.close();
        return null;
    }

    @Override
    public boolean existsByUserName(UserDto userDto) throws Exception {
        String query = "SELECT 1 FROM USER WHERE USERNAME = ?";
        PreparedStatement preparedStatement = MysqlConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, userDto.getUserName());
        ResultSet resulSet = preparedStatement.executeQuery();
        boolean exists = resulSet.next();
        resulSet.close();
        preparedStatement.close();
        return exists;
    }

    @Override
    public void createUser(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        String query = "INSERT INTO USER(PERSONNID,USERNAME,PASSWORD,ROLE) VALUES (?,?,?,?) ";
        PreparedStatement preparedStatement = MysqlConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, user.getPersonId().getId());
        preparedStatement.setString(2, user.getUserName());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.setString(4, user.getRole());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void deleteUser(UserDto userDto) throws Exception {
        User user = Helper.parse(userDto);
        String query = "DELETE FROM USER WHERE PERSONNID = ?";
        PreparedStatement preparedStatement = MysqlConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, user.getPersonId().getId());
        preparedStatement.execute();
        preparedStatement.close();

    }

    @Override
    public void updateUserRole(UserDto userDto) throws Exception {
        String query = "UPDATE USER SET ROLE = ? WHERE USERNAME = ?";

        PreparedStatement preparedStatement = MysqlConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, userDto.getRole());
        preparedStatement.setString(2, userDto.getUserName());
        preparedStatement.executeUpdate();
    }
}
