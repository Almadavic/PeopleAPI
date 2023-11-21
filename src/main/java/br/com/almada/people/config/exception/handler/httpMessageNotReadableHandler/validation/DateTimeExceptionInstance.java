package br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.validation;



import br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import br.com.almada.people.config.exception.standardError.commomStandardError.StandardError;
import org.springframework.http.ResponseEntity;

import java.time.DateTimeException;
import java.time.Instant;


public class DateTimeExceptionInstance extends FindExceptionInstance {

    public DateTimeExceptionInstance(FindExceptionInstance nextOne) {
        super(nextOne);
    }

    @Override
    public ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args) {

         Throwable throwable = args.rootCause();

        if (throwable instanceof DateTimeException) {

            return ResponseEntity.status(status).body(new StandardError(
                    Instant.now(),
                    status.value(),
                    "Invalid value to date",
                    throwable.getMessage(),
                    args.request().getRequestURI()));

        }

        return nextOne.verification(args);

    }

}

