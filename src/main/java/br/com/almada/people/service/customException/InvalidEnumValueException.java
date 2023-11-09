package br.com.almada.people.service.customException;

import java.io.Serial;

public class InvalidEnumValueException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidEnumValueException(String msg) {
        super(msg);
    }

    public InvalidEnumValueException(String value, String enumType, String listEnums) {
        super("The value you sent: " + value + " to the type " + enumType + " is not valid, valid values: " + listEnums);
    }

}
