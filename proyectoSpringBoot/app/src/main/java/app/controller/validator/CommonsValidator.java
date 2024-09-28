package app.controller.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CommonsValidator {
    public void isValidString(String element, String value) throws Exception {
        if (value.equals("")) {
            throw new Exception(element + " no puede ser un valor vacio");
        }
    }

    public int isValidInteger(String element, String value) throws Exception {
        isValidString(element, value);
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new Exception(element + " debe ser un valor valido");
        }
    }

    public long isValidLong(String element, String value) throws Exception {
        isValidString(element, value);
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            throw new Exception(element + " debe ser un valor valido");
        }
    }

    public double isValidDouble(String element, String value) throws Exception {
        isValidString(element, value);
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            throw new Exception(element + " debe ser un valor valido");
        }
    }

    public void isValidDate(String element, String date, String format) throws Exception {
        isValidString(element, date);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);

        try {
            sdf.parse(date);
        } catch (ParseException e) {
            throw new Exception(element + " debe ser una fecha válida en el formato " + format + ".");
        }
    }
}