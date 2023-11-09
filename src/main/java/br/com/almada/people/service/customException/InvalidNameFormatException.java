package br.com.almada.people.service.customException;

import java.io.Serial;

public class InvalidNameFormatException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidNameFormatException(String name) {
        super("The name: " + name + " contains an invalid format");
    }

}
