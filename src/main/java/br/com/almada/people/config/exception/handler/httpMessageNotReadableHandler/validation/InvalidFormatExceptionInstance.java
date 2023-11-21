package br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.validation;


import br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import br.com.almada.people.config.exception.standardError.commomStandardError.StandardError;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public class InvalidFormatExceptionInstance extends FindExceptionInstance {

    public InvalidFormatExceptionInstance(FindExceptionInstance nextOne) {
        super(nextOne);
    }

    @Override
    public ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args) {

        Throwable rootCause = args.rootCause();

        if (rootCause instanceof InvalidFormatException ex) {

            String path = joinPath(ex.getPath());

            String message = String.format("The property '%s' received the value '%s', which is of an invalid type. " +
                            "Correct and enter a value compatible with type %s.", path, ex.getValue(),
                    ex.getTargetType().getSimpleName());

            return ResponseEntity.status(status).body(new StandardError(
                    Instant.now(),
                    status.value(),
                    "Invalid value format",
                    message,
                    args.request().getRequestURI()));
        }

        return nextOne.verification(args);

    }
    
}
