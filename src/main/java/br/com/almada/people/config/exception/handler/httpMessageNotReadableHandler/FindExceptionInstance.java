package br.com.almada.people.config.exception.handler.httpMessageNotReadableHandler;

import br.com.almada.people.config.exception.standardError.commomStandardError.StandardError;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public abstract class FindExceptionInstance {

    protected FindExceptionInstance nextOne;

    protected static final HttpStatus status = HttpStatus.BAD_REQUEST;

    public abstract ResponseEntity<StandardError> verification(FindExceptionInstanceArgs args);

    protected String joinPath(List<JsonMappingException.Reference> references) {
        return references
                .stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));
    }

}
