
package app.Daoo;

import app.config.MYSQLConnection;
import app.dao.interfaces.PartnerDao;
import app.dto.PartnerDto;
import app.dto.PersonDto;

import app.dto.UserDto;
import app.helpers.Helper;
import app.model.Partner;
import app.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class PartnerDaoImplemetation implements PartnerDao {

    @Override
    public void createPartner(PartnerDto partnerDto) throws Exception {
        Partner partner = Helper.parse(partnerDto);
        String query = "INSERT INTO PARTNER(USERID,AMOUNT,TYPE,CREATIONDATE) VALUES (?, ?,?,?)";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partner.getUserId().getId());
        preparedStatement.setDouble(2, partner.getMoney());
        preparedStatement.setString(3, partner.getType());
        preparedStatement.setTimestamp(4, partner.getDateCreated());
        preparedStatement.execute();
        preparedStatement.close();
    }

    @Override
    public void deletePartner(PartnerDto partnerDto) throws Exception {
        Partner partner = Helper.parse(partnerDto);
        String query = "DELETE FROM PARTNER WHERE USERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, partner.getUserId().getId());
        preparedStatement.execute();
        preparedStatement.close();

    }

    @Override
    public PartnerDto existByPartner(UserDto userDto) throws Exception {
        String query = "SELECT ID,USERID,AMOUNT,TYPE,CREATIONDATE FROM PARTNER WHERE USERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setLong(1, userDto.getId());
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            Partner partner = new Partner();
            partner.setId(resulSet.getLong("ID"));
            partner.setMoney(resulSet.getDouble("AMOUNT"));
            partner.setType(resulSet.getString("TYPE"));
            partner.setDateCreated(resulSet.getTimestamp("CREATIONDATE"));
            User user = new User();
            user.setId(resulSet.getLong("USERID"));
            partner.setUserId(user);
            resulSet.close();
            preparedStatement.close();
            return Helper.parse(partner);
        }
        resulSet.close();
        preparedStatement.close();
        return null;
    }

    @Override
    public PartnerDto getMoneyByPartner(double getMoney) throws Exception {
        String query = "SELECT ID,USERID,AMOUNT,TYPE,CREATIONDATE FROM PARTNER WHERE AMOUNT = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setDouble(1, getMoney);
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            Partner partner = new Partner();
            partner.setId(resulSet.getLong("ID"));
            partner.setMoney(resulSet.getDouble("AMOUNT"));
            partner.setType(resulSet.getString("TYPE"));
            partner.setDateCreated(resulSet.getTimestamp("CREATIONDATE"));
            User user = new User();
            user.setId(resulSet.getLong("USERID"));
            partner.setUserId(user);
            resulSet.close();
            preparedStatement.close();
            return Helper.parse(partner);
        }
        resulSet.close();
        preparedStatement.close();
        return null;
    }

    @Override
    public void updateMoney(PartnerDto partnerDto) throws Exception {
        String query = "UPDATE PARTNER SET AMOUNT = ? WHERE USERID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setDouble(1, partnerDto.getMoney());
        preparedStatement.setLong(2, partnerDto.getUserId().getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public PartnerDto getTypeByPartner(PartnerDto partnerDto) throws Exception {
        String query = "SELECT ID,USERID,AMOUNT,TYPE,CREATIONDATE FROM PARTNER WHERE TYPE = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, partnerDto.getType());
        ResultSet resulSet = preparedStatement.executeQuery();
        if (resulSet.next()) {
            Partner partner = new Partner();
            partner.setId(resulSet.getLong("ID"));
            partner.setMoney(resulSet.getDouble("AMOUNT"));
            partner.setType(resulSet.getString("TYPE"));
            partner.setDateCreated(resulSet.getTimestamp("CREATIONDATE"));
            User user = new User();
            user.setId(resulSet.getLong("USERID"));
            partner.setUserId(user);
            resulSet.close();
            preparedStatement.close();
            return Helper.parse(partner);
        }
        resulSet.close();
        preparedStatement.close();
        return null;
    }

    @Override
    public int countVipPartners() throws Exception {
        String query = "SELECT COUNT(*) FROM PARTNER WHERE TYPE = 'vip'";
        try (PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query); ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        }
    }

    @Override
    public void updatePartnerType(PartnerDto partnerDto) throws Exception {
        String query = "UPDATE PARTNER SET TYPE = ? WHERE ID = ?";
        PreparedStatement preparedStatement = MYSQLConnection.getConnection().prepareStatement(query);
        preparedStatement.setString(1, partnerDto.getType());
        preparedStatement.setLong(2, partnerDto.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

}
