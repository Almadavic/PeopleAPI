package br.com.almada.people.dto.response;

import br.com.almada.people.entity.Person;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@JsonPropertyOrder(value = {"id", "name", "", "email", "age", "date_of_birth", "gender", "created_at", "updated_at", "address"})
@Getter
public class PersonResponseDTO extends RepresentationModel<PersonResponseDTO> {

    @JsonProperty(value = "id")
    private final String id;

    @JsonProperty(value = "name")
    private final String name;

    @JsonProperty(value = "email")
    private final String email;

    @JsonProperty(value = "date_of_birth")
    private final LocalDate dateOfBirth;

    @JsonProperty(value = "age")
    private final Integer age;

    @JsonProperty(value = "gender")
    private final String gender;

    @JsonProperty(value = "created_at")
    private final LocalDateTime createdAt;

    @JsonProperty(value = "updated_at")
    private final LocalDateTime updatedAt;

    @JsonProperty(value = "address")
    private final AddressResponseDTO address;

    public PersonResponseDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.email = person.getEmail();
        this.dateOfBirth = person.getDateOfBirth();
        this.age = calculateAge(person.getDateOfBirth());
        this.gender = person.getGender().toString();
        this.createdAt = person.getCreatedAt();
        this.updatedAt = person.getUpdatedAt();
        this.address = new AddressResponseDTO(person.getAddress());
    }

    private Integer calculateAge(LocalDate dateOfBirth) {
        Period period = Period.between(dateOfBirth, LocalDate.now());
        return period.getYears();
    }

}
