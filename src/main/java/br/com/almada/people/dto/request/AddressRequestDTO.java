package br.com.almada.people.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record AddressRequestDTO(

        @JsonProperty(value = "country")
        @NotBlank(message = "The field country needs to be filled")
        String country,

        @JsonProperty(value = "state")
        @NotBlank(message = "The field state needs to be filled")
        String state,

        @JsonProperty(value = "city")
        @NotBlank(message = "The field city needs to be filled")
        String city

) {
}
