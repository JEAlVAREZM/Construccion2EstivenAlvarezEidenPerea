
package app.controllers;

import app.controller.validator.PartnerValidator;
import app.controller.validator.PersonValidator;
import app.controller.validator.UserValidator;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.model.Invoice;
import app.service.interfac.AdminService;
import app.service.x.Service;
import java.sql.Date;
import java.sql.Timestamp;


public class AdminController implements ControllerInterface {

    private static final String MENU = "ingrese la opcion que desea ejecutar: \n 1. Para crear socio \n 2. Para cerrar sesion\n";
    private PersonValidator personValidator;
    private UserValidator userValidator;
    private AdminService service;
    private PartnerValidator partnerValidator;

    public AdminController() {
        this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
        this.service = new Service();
        this.partnerValidator = new PartnerValidator();

    }

    @Override
    public void session() throws Exception {
        boolean session = true;
        while (session) {
            session = menu();
        }

    }

    private boolean menu() {
        try {
            System.out.println("bienvenido " + Service.user.getUserName());
            System.out.print(MENU);
            String option = Utils.getReader().nextLine();
            return options(option);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private boolean options(String option) throws Exception {
        switch (option) {
            case "1": {
                this.createPartner();
                return true;
            }
            case "2": {
                System.out.println("se ha cerrado sesion");
                return false;
            }
            case "3": {
                System.out.println("se ha cerrado sesion");
                return false;
            }
            case "4": {

            }
            default: {
                System.out.println("ingrese una opcion valida");
                return true;
            }
        }
    }

    public void createPartner() throws Exception {
        System.out.println("Ingrese el nombre del socio");
        String name = Utils.getReader().nextLine();
        personValidator.validName(name);
        System.out.println("ingrese la cedula");
        long document = personValidator.validDocument(Utils.getReader().nextLine());
        long celPhone = ValidPhoneNumber();
        System.out.println("ingrese el usuario del socio");
        String userName = Utils.getReader().nextLine();
        userValidator.validUserName(userName);
        System.out.println("ingrese la contraseña ");
        String password = Utils.getReader().nextLine();
        userValidator.validUserName(password);
        PersonDto personDto = new PersonDto();
        personDto.setName(name);
        personDto.setDocument(document);
        personDto.setCelPhone(celPhone);
        UserDto userDto = new UserDto();
        userDto.setPersonId(personDto);
        userDto.setUserName(userName);
        userDto.setPassword(password);
        userDto.setRole("partner");
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setUserId(userDto);
        partnerDto.setMoney(50000);
        partnerDto.setDateCreated(new Timestamp(System.currentTimeMillis()));
        partnerDto.setType("regular");
        this.service.createPartner(partnerDto);
        System.out.println("se ha creado el usuario exitosamente ");
        System.out.println("Tipo de socio: " + partnerDto.getType());
        System.out.println("Sus ingresos actuales son de:" + partnerDto.getMoney());
        System.out.println("Se creo el socio en el dia y hora: " + partnerDto.getDateCreated());
    }

    private long ValidPhoneNumber() throws NumberFormatException {
        while (true) {
            System.out.println("Ingrese el número de celular (mínimo 10 dígitos):");
            String cellPhoneInput = Utils.getReader().nextLine();
            if (cellPhoneInput.matches("\\d{10,}")) { // Verifica que el input tenga al menos 10 dígitos
                return Long.parseLong(cellPhoneInput);
            } else {
                System.out.println("El número de celular debe tener al menos 10 dígitos. Inténtelo nuevamente.");
            }
        }
    }

}
