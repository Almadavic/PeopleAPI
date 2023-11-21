package br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.validation;


import br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.FindExceptionInstance;
import br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler.FindExceptionInstanceArgs;
import br.com.almada.people.config.exception.standardError.commomStandardError.StandardError;
import org.springframework.http.ResponseEntity;

import java.time.Instant;

public class GenericExceptionInstance extends FindExceptionInstance {

    public GenericExceptionInstance() {
        super(null);
    }

    @Override
    public ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args) {

        StandardError err = new StandardError(Instant.now(), status.value(), "Client error", args.exception().getMessage(), args.request().getRequestURI());

        return ResponseEntity.ok().body(err);
    }

}
