package app.service;

import app.config.MysqlConnection;
import app.dao.interfaces.InvoiceDao;
import app.dao.interfaces.PartnerDao;
import app.dao.interfaces.UserDao;
import app.dao.InvoiceDaoImplementation;
import app.dao.PartnerDaoImplementation;
import app.dao.UserDaoImplementation;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.UserDto;
import app.model.Invoice;
import app.service.interfaces.AdminService;
import app.service.interfaces.LoginService;

import java.sql.SQLException;
import java.util.List;

public class Service implements AdminService, LoginService {

    private final UserDao userDao;
    private final PartnerDao partnerDao;
    private final InvoiceDao invoiceDao;
    public static UserDto user;

    public Service() {
        this.userDao = new UserDaoImplementation(MysqlConnection.getConnection());
        this.partnerDao = new PartnerDaoImplementation();
        this.invoiceDao = new InvoiceDaoImplementation();
    }

    @Override
    public void registerPartner(PartnerDto partnerDto) throws Exception {
        UserDto userDto = userDao.findById(partnerDto.getId());
        if (userDto == null) {
            throw new Exception("No existe un usuario con ese id");
        }
        if (!"Regular".equalsIgnoreCase(partnerDto.getSubscriptionType()) &&
                !"VIP".equalsIgnoreCase(partnerDto.getSubscriptionType())) {
            throw new Exception("Tipo de suscripción inválido. Debe ser 'Regular' o 'VIP'.");
        }

        /*partnerDao.createPartner(partnerDto);*/
    }

    @Override
    public void login(UserDto userDto) throws Exception {
        UserDto validateDto = userDao.findByUserName(userDto.getUserName());
        if (validateDto == null) {
            throw new Exception("No existe usuario registrado");
        }
        if (!userDto.getPassword().equals(validateDto.getPassword())) {
            throw new Exception("Usuario o contraseña incorrecto");
        }
        userDto.setRole(validateDto.getRole());
        user = validateDto;
    }

    @Override
    public void logout() {
        user = null;
        System.out.println("Se ha cerrado sesión");
    }

    @Override
    public void createUser(UserDto userDto) throws Exception {
        if (userDao.existsByUserName(userDto.getUserName())) {
            throw new Exception("Ya existe un usuario con ese nombre de usuario.");
        }

        if (userDto.getRole() == null || userDto.getRole().isEmpty()) {
            userDto.setRole("partner");
        }

        if (userDto.getPersonId() == null) {
            Long newPersonId = generateNewPersonId();
            userDto.setPersonId(newPersonId);
        }

        try {
            userDao.createUser(userDto);
        } catch (SQLException e) {
            throw new Exception("Error al crear el usuario: " + e.getMessage(), e);
        }
    }

    @Override
    public List<InvoiceDto> getClubInvoiceHistory() throws Exception {
        return List.of();
    }

    @Override
    public List<InvoiceDto> getMemberInvoiceHistory(long partnerId) throws Exception {
        return List.of();
    }

    @Override
    public List<InvoiceDto> getGuestInvoiceHistory(long guestId) throws Exception {
        return List.of();
    }

    @Override
    public List<Invoice> getAllInvoices() throws SQLException {
        return List.of();
    }

    @Override
    public Invoice getInvoiceById(long invoiceId) throws SQLException {
        return null;
    }

    private Long generateNewPersonId() {
        return System.currentTimeMillis();
    }


    @Override
    public boolean isUserPresent(String userName) throws Exception {
        return userDao.existsByUserName(userName);
    }

    @Override
    public void viewInvoiceHistory() throws Exception {
        System.out.println("Historial de facturas:");
    }

    @Override
    public void promoteToVIP(UserDto userDto) throws Exception {

    }

    public void promoteToVIP() throws Exception {
        System.out.println("Promoción a VIP:");
    }
}
