package br.com.almada.people.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@JsonPropertyOrder(value = {"name", "email", "date_of_birth", "gender", "address"})
public record PersonRequestDTO(

        @JsonProperty(value = "name")
        @NotNull
        @Size(min = 4, message = "The field name needs to have at least 4 characters")
        String name,

        @JsonProperty(value = "email")
        @NotNull
        @Email(message = "The field email needs to contain a valid e-mail format")
        String email,

        @JsonProperty(value = "date_of_birth")
        @Past
        LocalDate dateOfBirth,

        @JsonProperty(value = "gender")
        @NotNull
        @Size(min = 4, message = "The field gender needs to have at least 4 characters")
        String gender,

        @JsonProperty(value = "address") @Valid
        AddressRequestDTO address

) {
}
