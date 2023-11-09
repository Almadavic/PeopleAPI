package br.com.almada.people.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@JsonPropertyOrder(value = {"status", "data"})
@Getter
public class StatusDataResponseDTO<T> {

    @JsonProperty(value = "status")
    private final StatusResponseDTO responseStatus;

    @JsonProperty(value = "data")
    private final T data;

    public StatusDataResponseDTO(String message, T data) {
        this.responseStatus = new StatusResponseDTO(message);
        this.data = data;
    }

}
