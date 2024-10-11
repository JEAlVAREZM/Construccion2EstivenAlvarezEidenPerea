/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller.validator;

public class UserValidator extends CommonsValidator {

    public UserValidator() {
        super();
    }

    public void validUserName(String userName) throws Exception {
        super.isValidString("el nombre de usuario ", userName);
    }

    public void validPassword(String password) throws Exception {
        super.isValidString("la contraseña de usuario ", password);
    }

    public void validRole(String role) throws Exception {
        super.isValidString("el rol de usuario ", role);
    }

}
