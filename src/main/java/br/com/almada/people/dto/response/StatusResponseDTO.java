package br.com.almada.people.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonPropertyOrder(value = {"message", "response_time"})
@Getter
public class StatusResponseDTO {

    @JsonProperty(value = "message")
    private final String message;

    @JsonProperty(value = "response_time")
    private final LocalDateTime responseTime;

    public StatusResponseDTO(String message) {
        this.message = message;
        this.responseTime = LocalDateTime.now();
    }

}
