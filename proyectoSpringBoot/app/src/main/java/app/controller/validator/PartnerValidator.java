package app.controller.validator;

public class PartnerValidator extends CommonsValidator {
    public PartnerValidator() {
        super();
    }
    public void validName(String name) throws Exception {
        super.isValidString("nombre del socio", name);
    }
    public void validIdNumber(String idNumber) throws Exception {
        super.isValidInteger("cédula del socio", idNumber);
    }
    public void validPhoneNumber(String phoneNumber) throws Exception {
        if (phoneNumber == null || !phoneNumber.matches("\\d{10}")) {
            throw new Exception("Número de teléfono inválido. Debe tener 10 dígitos.");
        }
    }
    public double validFunds(String funds) throws Exception {
        double amount = super.isValidDouble("fondos disponibles del socio", funds);
        if (amount < 0) {
            throw new Exception("Los fondos disponibles no pueden ser negativos.");
        }
        return amount;
    }
    public void validSubscriptionType(String subscriptionType) throws Exception {
        if (!"Regular".equalsIgnoreCase(subscriptionType) && !"VIP".equalsIgnoreCase(subscriptionType)) {
            throw new Exception("Tipo de suscripción inválido. Debe ser 'Regular' o 'VIP'.");
        }
    }
    public void validAffiliationDate(String affiliationDate) throws Exception {
        try {
            super.isValidDate("fecha de afiliación", affiliationDate, "yyyy-MM-dd");
        } catch (IllegalArgumentException e) {
            throw new Exception("Fecha de afiliación inválida. Debe seguir el formato YYYY-MM-DD.");
        }
    }
}