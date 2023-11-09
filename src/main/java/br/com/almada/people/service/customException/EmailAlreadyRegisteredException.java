package br.com.almada.people.service.customException;

import java.io.Serial;

public class EmailAlreadyRegisteredException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }

}
