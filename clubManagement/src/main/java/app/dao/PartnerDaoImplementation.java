package app.dao;

import app.dao.interfaces.PartnerDao;
import app.dto.PartnerDto;
import app.model.Partner;
import app.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartnerDaoImplementation implements PartnerDao {
    private Connection connection;

    public PartnerDaoImplementation(Connection connection) {
        this.connection = connection;
    }

    public PartnerDaoImplementation() {

    }

    @Override
    public void createPartner(PartnerDto partnerDto) throws Exception {
        String sql = "INSERT INTO partner (userid, amount, type, creationdate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            // Usamos el ID del User
            statement.setLong(1, partnerDto.getUserId());
            statement.setDouble(2, partnerDto.getAvailableMoney());
            statement.setString(3, partnerDto.getSubscriptionType());
            statement.setTimestamp(4, new Timestamp(partnerDto.getAffiliationDate().getTime()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        partnerDto.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            // Aquí puedes registrar el error o lanzar una excepción personalizada
            throw new Exception("Error al crear el socio: " + e.getMessage(), e);
        }
    }


    @Override
    public Partner findPartnerById(long partnerId) throws Exception {
        String sql = "SELECT * FROM partner WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, partnerId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Partner partner = new Partner();
                    partner.setId(resultSet.getLong("ID"));
                    User user = new User();
                    user.setId(resultSet.getLong("USERID"));
                    partner.setUserId(user);
                    partner.setAvailableMoney(resultSet.getDouble("AMOUNT"));
                    partner.setSubscriptionType(resultSet.getString("TYPE"));
                    partner.setAffiliationDate(resultSet.getDate("CREATIONDATE"));

                    return partner;
                } else {
                    throw new Exception("Socio no encontrado.");
                }
            }
        }
    }

    @Override
    public void updatePartner(Partner partner) throws Exception {
        String sql = "UPDATE partner SET USERID = ?, AMOUNT = ?, TYPE = ?, CREATIONDATE = ?, ACTIVE = ? WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, partner.getUserId().getId()); // Usamos el ID del User
            statement.setDouble(2, partner.getAvailableMoney());
            statement.setString(3, partner.getSubscriptionType());
            statement.setTimestamp(4, new Timestamp(partner.getAfiliationDate().getTime()));
            statement.setLong(6, partner.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deletePartner(long partnerId) throws Exception {
        String sql = "DELETE FROM partner WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, partnerId);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Partner> findAllPartners() throws Exception {
        String sql = "SELECT * FROM partner";
        List<Partner> partners = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Partner partner = new Partner();
                partner.setId(resultSet.getLong("ID"));
                User user = new User();
                user.setId(resultSet.getLong("USERID"));
                partner.setUserId(user);
                partner.setAvailableMoney(resultSet.getDouble("AMOUNT"));
                partner.setSubscriptionType(resultSet.getString("TYPE"));
                partner.setAffiliationDate(resultSet.getDate("CREATIONDATE"));
                partners.add(partner);
            }
        }
        return partners;
    }
}
