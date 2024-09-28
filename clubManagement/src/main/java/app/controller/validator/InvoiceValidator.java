
package app.controller.validator;


public class InvoiceValidator extends CommonsValidator {

    public InvoiceValidator() {
        super();
    }

    public double validAmount(String amount) throws Exception {
        return super.isValidDouble("el monto de la factura ", amount);
    }
}
