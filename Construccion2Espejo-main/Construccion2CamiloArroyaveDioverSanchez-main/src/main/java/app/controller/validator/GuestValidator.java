
package app.controller.validator;


public class GuestValidator extends CommonsValidator {

    public GuestValidator() {
        super();
    }

    public void validUserName(String userName) throws Exception {
        super.isValidString("El nombre de usuario ", userName);
    }

    public long validGuestStatus(String id) throws Exception {
        return super.isValidLong("La id esquita ", id);
    }
}
