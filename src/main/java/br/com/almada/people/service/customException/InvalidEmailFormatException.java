package br.com.almada.people.service.customException;

import java.io.Serial;

public class InvalidEmailFormatException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidEmailFormatException(String email) {
        super("The e-mail: " + email + " contains an invalid format");
    }

}
