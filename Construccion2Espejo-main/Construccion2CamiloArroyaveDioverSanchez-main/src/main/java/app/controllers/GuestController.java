/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controllers;

import app.controller.validator.PartnerValidator;
import app.controller.validator.PersonValidator;
import app.controller.validator.UserValidator;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.service.interfac.AdminService;
import app.service.interfac.PartnerService;
import app.service.x.Service;
import java.sql.Timestamp;

public class GuestController implements ControllerInterface {

    private static final String MENU = "ingrese la opcion que desea ejecutar:  \n 1. Pasar a Socio \n 2. Para cerrar sesion\n";
    private PersonValidator personValidator;
    private UserValidator userValidator;
    private AdminService service;
    private PartnerService servic;
    private PartnerValidator partnerValidator;

    public GuestController() {
        this.personValidator = new PersonValidator();
        this.userValidator = new UserValidator();
        this.service = new Service();
        this.partnerValidator = new PartnerValidator();
        this.servic = new Service();
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
            case "": {
                System.out.println("");
                return true;
            }
            case "1": {
                this.createPartner();
                return true;
            }
            case "2": {
                System.out.println("se ha cerrado sesion");
                return false;
            }

            default: {
                System.out.println("ingrese una opcion valida");
                return true;
            }
        }
    }

    public void createPartner() throws Exception {
        UserDto userDto = Service.user;
        userDto.setRole("partner");
        PartnerDto partnerDto = new PartnerDto();
        partnerDto.setUserId(userDto);
        partnerDto.setMoney(50000);
        partnerDto.setDateCreated(new Timestamp(System.currentTimeMillis()));
        partnerDto.setType("regular");

        System.out.println("se ha creado el usuario exitosamente ");
        System.out.println("Tipo de socio: " + partnerDto.getType());
        System.out.println("Sus ingresos actuales son de:" + partnerDto.getMoney());
        System.out.println("Se creo el socio en el dia y hora: " + partnerDto.getDateCreated());
        this.servic.changeRol(partnerDto);
    }

    public void deleteGuest() throws Exception {

    }
}
