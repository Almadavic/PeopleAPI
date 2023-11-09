package br.com.almada.people.config.exception.standardError.beanValidationStandardError;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder(value = {"field", "message"})
public record StandardErrorFieldsNotValid(
        @JsonProperty(value = "field")
        String field,

        @JsonProperty(value = "message")
        String message

) {
}
