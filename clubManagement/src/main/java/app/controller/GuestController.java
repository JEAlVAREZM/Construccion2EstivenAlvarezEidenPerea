package app.controller;

import java.util.Scanner;

public class GuestController implements ControllerInterface {
    private Scanner scanner;

    public GuestController() {
        this.scanner = new Scanner(System.in);
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
            System.out.println("Menu del invitado: \n1. Opción 1 \n2. Opción 2 \n3. Salir");
            String option = scanner.nextLine();
            return options(option);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

    private boolean options(String option) throws Exception {
        switch (option) {
            case "1":
                System.out.println("Opción 1 seleccionada.");
                return true;
            case "2":
                System.out.println("Opción 2 seleccionada.");
                return true;
            case "3":
                System.out.println("Se detiene la sesión del invitado.");
                return false;
            default:
                System.out.println("Ingrese una opción válida.");
                return true;
        }
    }
}
