package app.controller;

import app.config.MysqlConnection;
import app.dao.AdminDaoImplementation;
import app.dao.PartnerDaoImplementation;
import app.dao.PersonDaoImplementation;
import app.dao.UserDaoImplementation;
import app.dao.interfaces.PartnerDao;
import app.dao.interfaces.UserDao;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.dto.UserDto;
import app.model.Invoice;
import app.service.Service;
import app.service.interfaces.AdminService;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import app.dao.interfaces.PersonDao;
import java.util.Calendar;



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
		// Solicitar los datos de la persona
		System.out.println("Ingrese el nombre:");
		String nombre = scanner.nextLine();

		System.out.println("Ingrese el número de documento:");
		long document = scanner.nextLong();
		scanner.nextLine();  // Consumir la línea pendiente

		System.out.println("Ingrese el celular:");
		long cellphone = scanner.nextLong();
		scanner.nextLine();  // Consumir la línea pendiente

		// Solicitar los datos del usuario
		System.out.println("Ingrese el usuario:");
		String user = scanner.nextLine();

		System.out.println("Ingrese el password:");
		String password = scanner.nextLine();

		// Crear un objeto PersonDto con los datos ingresados
		PersonDto personDto = new PersonDto();
		personDto.setName(nombre);
		personDto.setDocument(document);
		personDto.setPhone(cellphone);

		try {
			// Crear una instancia de PersonDaoImplementation con una conexión a la base de datos
			PersonDao personDao = new PersonDaoImplementation(MysqlConnection.getConnection());

			// Llamar al método createPerson para insertar los datos en la base de datos
			personDao.createPerson(personDto);
			System.out.println("Persona registrada exitosamente con ID: " + personDto.getId());

			// Crear un objeto UserDto con los datos ingresados y asignar el ID de la persona recién creada
			UserDto userDto = new UserDto();
			userDto.setUserName(user);
			userDto.setPassword(password);
			userDto.setRole("partner");
			userDto.setPersonId(personDto.getId());  // Relacionar usuario con la persona creada

			// Crear la instancia de UserDaoImplementation para insertar el usuario
			UserDao userDao = new UserDaoImplementation(MysqlConnection.getConnection());
			userDao.createUser(userDto);
			System.out.println("Usuario registrado exitosamente con ID: " + userDto.getId());

			// Asegurarse de que el ID del usuario se haya generado y asignado correctamente
			if (userDto.getId() == null) {
				throw new Exception("Error: No se pudo obtener el ID del usuario después de la inserción.");
			}

			// Crear un objeto PartnerDto con los datos ingresados
			Calendar calendar = Calendar.getInstance();
			java.util.Date today = calendar.getTime(); // Fecha actual en java.util.Date
			java.sql.Timestamp timestamp = new java.sql.Timestamp(today.getTime());

			PartnerDto partnerDto = new PartnerDto();
			partnerDto.setAvailableMoney(50000);
			partnerDto.setSubscriptionType("standard");
			partnerDto.setAffiliationDate(timestamp);
			partnerDto.setUserId(userDto.getId()); // Relacionar partner con el usuario recién creado

			// Crear la instancia de PartnerDaoImplementation para insertar el partner
			PartnerDao partnerDao = new PartnerDaoImplementation(MysqlConnection.getConnection());
			partnerDao.createPartner(partnerDto);
			System.out.println("Partner registrado exitosamente con ID: " + partnerDto.getId());

		} catch (SQLException e) {
			System.err.println("Error al guardar en la base de datos: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("Error inesperado: " + e.getMessage());
			e.printStackTrace();
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