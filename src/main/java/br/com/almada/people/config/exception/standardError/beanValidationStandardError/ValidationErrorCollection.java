package br.com.almada.people.config.exception.standardError.beanValidationStandardError;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.Instant;
import java.util.List;


@JsonPropertyOrder(value = {"timestamp", "status", "error", "validationErrors", "path"})
public record ValidationErrorCollection(

        @JsonProperty(value = "timestamp")
        Instant timestamp,

        @JsonProperty(value = "status")
        Integer status,

        @JsonProperty(value = "error")
        String error,

        @JsonProperty(value = "path")
        String path,

        @JsonProperty(value = "validationErrors")
        List<StandardErrorFieldsNotValid> validationErrors

) {
}

