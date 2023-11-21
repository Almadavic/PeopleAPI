package br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.validation;

import br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import br.com.almada.people.config.exception.standardError.commomStandardError.StandardError;
import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public class JsonParseExceptionInstance extends FindExceptionInstance {


    public JsonParseExceptionInstance(FindExceptionInstance nextOne) {
        super(nextOne);
    }

    @Override
    public ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args) {

        Throwable rootCause = args.rootCause();

        if (rootCause instanceof JsonParseException) {

            return ResponseEntity.status(status).body(new StandardError(
                    Instant.now(),
                    status.value(),
                    "JSON error",
                    "Verify the JSON format",
                    args.request().getRequestURI()));
        }

        return nextOne.verification(args);

    }

}

