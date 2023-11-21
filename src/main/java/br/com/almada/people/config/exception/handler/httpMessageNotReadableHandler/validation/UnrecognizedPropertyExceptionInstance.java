package br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.validation;

import br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import br.com.almada.people.config.exception.standardError.commomStandardError.StandardError;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public class UnrecognizedPropertyExceptionInstance extends FindExceptionInstance {

    public UnrecognizedPropertyExceptionInstance(FindExceptionInstance nextOne) {
        super(nextOne);
    }

    @Override
    public ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args) {

        if (args.rootCause() instanceof UnrecognizedPropertyException) {

            String[] splitError = args.exception().getMessage().split(" ");

            return ResponseEntity.status(status).body(new StandardError(
                    Instant.now(),
                    status.value(),
                    "Field not recognized: " + splitError[5].replace("\"", ""),
                    "Name (key) of invalid fields are not accepted",
                    args.request().getRequestURI()));
        }

        return nextOne.verification(args);

    }

}
