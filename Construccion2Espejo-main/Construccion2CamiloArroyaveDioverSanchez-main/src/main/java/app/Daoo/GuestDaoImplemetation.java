
package app.Daoo;

import app.config.MYSQLConnection;
import app.dao.interfaces.GuestDao;
import app.dao.interfaces.PartnerDao;
import app.dto.GuestDto;
import app.dto.PartnerDto;
import app.dto.UserDto;
import app.helpers.Helper;
import app.model.Guest;
import app.model.Partner;
import app.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class GuestDaoImplemetation implements GuestDao {

    @Override
    public void createGuest(GuestDto guestDto) throws SQLException {
        Guest guest = Helper.parse(guestDto);
        String query = "INSERT INTO GUEST (USERID,PARTNERID,STATUS) VALUES (?, ?,?)";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, guest.getUserId().getId());
        preparedStatement.setLong(2, guest.getPartnerId().getId());
        preparedStatement.setString(3, guest.getStatus());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void deleteGuest(GuestDto guestDto) throws Exception {
        Guest guest = Helper.parse(guestDto);
        String query = "DELETE FROM GUEST WHERE USERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, guest.getUserId().getId());
        preparedStatement.execute();
        preparedStatement.close();

    }

    @Override
    public GuestDto existByGuest(UserDto userDto) throws Exception {
        String query = "SELECT ID,USERID,PARTNERID,STATUS FROM GUEST WHERE USERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, userDto.getId());
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            Guest guest = new Guest();
            guest.setId(resulSet.getLong("ID"));
            guest.setStatus(resulSet.getString("STATUS"));
            User user = new User();
            user.setId(resulSet.getLong("USERID"));
            guest.setUserId(user);
            Partner partner = new Partner();
            partner.setId(resulSet.getLong("PARTNERID"));
            guest.setPartnerId(partner);
            resulSet.close();
            preparedStatement.close();
            return Helper.parse(guest);
        }
        resulSet.close();
        preparedStatement.close();
        return null;
    }

    @Override
    public List<GuestDto> statusGuest(PartnerDto partnerDto) throws Exception {
        List<GuestDto> guests = new ArrayList<>();
        String query = "SELECT ID,USERID,PARTNERID,STATUS FROM GUEST WHERE PARTNERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partnerDto.getId());
        ResultSet resulSet = preparedStatement.executeQuery();
        while (resulSet.next()) {
            Guest guest = new Guest();
            guest.setId(resulSet.getLong("ID"));
            guest.setStatus(resulSet.getString("STATUS"));
            User user = new User();
            user.setId(resulSet.getLong("USERID"));
            guest.setUserId(user);
            Partner partner = new Partner();
            partner.setId(resulSet.getLong("PARTNERID"));
            guest.setPartnerId(partner);
            guests.add(Helper.parse(guest));
        }
        resulSet.close();
        preparedStatement.close();
        return guests;
    }

    @Override
    public void changeStatus(GuestDto guestDto) throws Exception {
        String query = "UPDATE GUEST SET STATUS = ? WHERE USERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, guestDto.getStatus());
        preparedStatement.setLong(2, guestDto.getUserId().getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public GuestDto getGuestById(long guestId) throws Exception {
        String query = "SELECT ID,USERID,PARTNERID,STATUS FROM GUEST WHERE ID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, guestId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Guest guest = new Guest();
            guest.setId(resultSet.getLong("ID"));
            guest.setStatus(resultSet.getString("STATUS"));
            User user = new User();
            user.setId(resultSet.getLong("USERID"));
            guest.setUserId(user);
            Partner partner = new Partner();
            partner.setId(resultSet.getLong("PARTNERID"));
            guest.setPartnerId(partner);

            resultSet.close();
            return Helper.parse(guest);
        } else {
            resultSet.close();
            return null;

        }
    }

    @Override
    public List<GuestDto> getGuestsByPartnerId(long partnerId) throws Exception {
        List<GuestDto> guests = new ArrayList<>();
        String query = "SELECT ID,USERID,PARTNERID,STATUS FROM GUEST WHERE PARTNERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partnerId);
        ResultSet resulSet = preparedStatement.executeQuery();
        while (resulSet.next()) {
            Guest guest = new Guest();
            guest.setId(resulSet.getLong("ID"));
            guest.setStatus(resulSet.getString("STATUS"));
            User user = new User();
            user.setId(resulSet.getLong("USERID"));
            guest.setUserId(user);
            Partner partner = new Partner();
            partner.setId(resulSet.getLong("PARTNERID"));
            guest.setPartnerId(partner);
            guests.add(Helper.parse(guest));
        }
        resulSet.close();
        preparedStatement.close();
        return guests;
    }

    @Override
    public int countGuestsByPartnerId(long partnerId) throws Exception {
         String query = "SELECT COUNT(*) AS count FROM GUEST WHERE PARTNERID = ? AND STATUS = 'activo'";
    PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
    preparedStatement.setLong(1, partnerId);
    ResultSet resultSet = preparedStatement.executeQuery();
    int count = 0;
    if (resultSet.next()) {
        count = resultSet.getInt("count");
    }
    resultSet.close();
    preparedStatement.close();
    return count;
    }


    }

        
    
  

   
