package br.com.almada.people.config.exception.standardError.commomStandardError;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.Instant;

@JsonPropertyOrder(value = {"timestamp", "status", "error", "message", "path"})
public record StandardError(

        @JsonProperty(value = "timestamp")
        Instant timestamp,

        @JsonProperty(value = "status")
        Integer status,

        @JsonProperty(value = "error")
        String error,

        @JsonProperty(value = "message")
        String message,

        @JsonProperty(value = "path")
        String path

) { }
