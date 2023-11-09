package br.com.almada.people.service.customException;

import java.io.Serial;

public class InvalidPasswordException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidPasswordException() {
        super("The password you entered is wrong");
    }

}
