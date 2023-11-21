package br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.validation;

import br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import br.com.almada.people.config.exception.standardError.commomStandardError.StandardError;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public class MismatchedInputExceptionInstance extends FindExceptionInstance {

    public MismatchedInputExceptionInstance(FindExceptionInstance nextOne) {
        super(nextOne);
    }

    @Override
    public ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args) {

        if (args.rootCause() instanceof MismatchedInputException ex) {

            String path = joinPath(ex.getPath());

            String message = String.format("The property '%s' received a invalid value " +
                            "Correct and enter a value compatible with type %s.", path,
                    ex.getTargetType().getSimpleName());

            return ResponseEntity.status(status).body(new StandardError(
                    Instant.now(),
                    status.value(),
                    "Invalid type for list",
                    message,
                    args.request().getRequestURI()));

        }

        return nextOne.verification(args);
    }

}
