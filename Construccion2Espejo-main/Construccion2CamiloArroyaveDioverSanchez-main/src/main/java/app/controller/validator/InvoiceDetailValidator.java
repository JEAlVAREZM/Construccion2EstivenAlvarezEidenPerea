
package app.controller.validator;


public class InvoiceDetailValidator extends CommonsValidator {

    public InvoiceDetailValidator() {
        super();

    }

    public void validItems(String items) throws Exception {
        super.isValidString("items de la factura", items);
    }
}
