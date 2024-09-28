
package app.controller.validator;


public class PartnerValidator extends CommonsValidator {

    public PartnerValidator() {
        super();
    }

    public double validMoney(String money) throws Exception {
        return super.isValidDouble("El valor ingresado", money);
    }

}
