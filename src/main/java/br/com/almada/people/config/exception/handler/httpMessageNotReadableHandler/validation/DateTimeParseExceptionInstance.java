package br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.validation;


import br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import br.com.almada.people.config.exception.standardError.commomStandardError.StandardError;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.time.format.DateTimeParseException;


public class DateTimeParseExceptionInstance extends FindExceptionInstance {

    public DateTimeParseExceptionInstance(FindExceptionInstance nextOne) {
        super(nextOne);
    }

    @Override
    public ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args) {


        if (args.rootCause() instanceof DateTimeParseException ex) {

            return ResponseEntity.status(status).body(new StandardError(
                    Instant.now(),
                    status.value(),
                    "Invalid Date Format",
                    menageMessageException(args.exception(), ex),
                    args.request().getRequestURI()));

        }

        return nextOne.verification(args);

    }

    private String menageMessageException(Exception exception, DateTimeParseException ex) {

        String message;

        if(exception.getMessage().contains("LocalTime")) {
            message = "Received value " + ex.getParsedString() + " for time, which is an invalid type. Proper time format: 00:00";
        } else {
            message = "Received value " + ex.getParsedString() + " for date, which is an invalid type. Proper date format: yyyy-mm-dd";
        }

        return message;
    }

}

