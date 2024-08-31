package app.controller;

import app.dto.UserDto;
import app.model.Invoice;
import app.service.Service;
import app.service.interfaces.AdminService;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AdminController implements ControllerInterface {
	private Scanner scanner;
	private AdminService adminService;

	public AdminController() {
		this.scanner = new Scanner(System.in);
		this.adminService = new Service();
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
			System.out.println("Menu del Administrador: \n1. Crear Socio \n2. Historial de Facturas \n3. Promoción a VIP \n4. Salir");
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
				registerMember();
				return true;
			case "2":
				viewInvoiceHistory();
				return true;
			case "3":
				promoteToVIP();
				return true;
			case "4":
				System.out.println("Se detiene la sesión del administrador.");
				return false;
			default:
				System.out.println("Ingrese una opción válida.");
				return true;
		}
	}

	private void registerMember() throws Exception {
		System.out.println("Ingrese el número de documento:");
		String document = scanner.nextLine();
		System.out.println("Ingrese la contraseña:");
		String password = scanner.nextLine();

		if (document.isEmpty() || password.isEmpty()) {
			System.out.println("El nombre de usuario y la contraseña no pueden estar vacíos.");
			return;
		}

		UserDto userDto = new UserDto();
		userDto.setUserName(document);
		userDto.setPassword(password);

		try {
			adminService.createUser(userDto);
			System.out.println("Socio registrado exitosamente.");
		} catch (Exception e) {
			System.out.println("Error al registrar el socio: " + e.getMessage());
		}
	}

	private void viewInvoiceHistory() throws Exception {
		boolean viewingInvoice = true;
		while (viewingInvoice) {
			System.out.println("=== Historial de Facturas ===");
			System.out.println("1. Ver todas las facturas");
			System.out.println("2. Ver detalles de una factura específica");
			System.out.println("3. Salir");

			System.out.print("Seleccione una opción: ");
			int choice = scanner.nextInt();
			scanner.nextLine();
			switch (choice) {
				case 1:
					showAllInvoices();
					break;
				case 2:
					System.out.print("Ingrese el ID de la factura: ");
					long invoiceId = scanner.nextLong();
					scanner.nextLine();
					showInvoiceDetails(invoiceId);
					break;
				case 3:
					viewingInvoice = false;
					System.out.println("Saliendo del historial de facturas...");
					break;
				default:
					System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
					break;
			}
		}
	}

	private void showAllInvoices() throws SQLException {
		List<Invoice> invoices = adminService.getAllInvoices();
		for (Invoice invoice : invoices) {
			System.out.println("Factura ID: " + invoice.getId() +
					", Monto: " + invoice.getAmount() +
					", Fecha: " + invoice.getDate() +
					", Estado: " + invoice.getStateInvoice());
		}
	}

	private void showInvoiceDetails(long invoiceId) throws SQLException {
		Invoice invoice = adminService.getInvoiceById(invoiceId);
		if (invoice != null) {
			System.out.println("=== Detalles de la Factura ===");
			System.out.println("Factura ID: " + invoice.getId());
			System.out.println("Persona: " + invoice.getPersonId().getName());
			System.out.println("Socio: " + invoice.getPartnerId().getUserId().getUserName());
			System.out.println("Monto: " + invoice.getAmount());
			System.out.println("Fecha: " + invoice.getDate());
			System.out.println("Estado: " + invoice.getStateInvoice());
		} else {
			System.out.println("Factura no encontrada.");
		}
	}

	private void promoteToVIP() {
		System.out.println("Promoción a VIP:");
	}
}