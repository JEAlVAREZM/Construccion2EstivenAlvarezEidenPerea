/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClubProject;

import app.config.MYSQLConnection;
import app.controllers.ControllerInterface;
import app.controllers.LoginController;
/**
 *
 * @author Camilo
 */
public class App {
    public static void main(String[] args) {
        // TODO code application logic here]

		ControllerInterface controller = new LoginController();
		try {
			controller.session();
			MYSQLConnection.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

