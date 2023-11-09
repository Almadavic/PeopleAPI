package br.com.almada.people.factory;

import br.com.almada.people.dto.request.AddressRequestDTO;
import br.com.almada.people.dto.request.PersonRequestDTO;
import br.com.almada.people.dto.response.PersonResponseDTO;
import br.com.almada.people.entity.Person;
import br.com.almada.people.enumerated.Gender;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PersonFactory {

    public PersonRequestDTO requestDTO() {
        return new PersonRequestDTO("Victor Almada", "almadavic@live.com", LocalDate.of(2001, 8, 11), "MALE",
                new AddressRequestDTO("Brasil", "Minas Gerais", "Belo Horizonte"));
    }

    public PersonRequestDTO requestDTO2() {
        return new PersonRequestDTO("Victor Almada", "almadavic@hotmail.com", LocalDate.of(2001, 8, 11), "MALE",
                new AddressRequestDTO("Brasil", "Minas Gerais", "Belo Horizonte"));
    }

    public Person entity() {
        return Person.builder()
                .name("Victor Almada")
                .email("almadavic@live.com")
                .dateOfBirth(LocalDate.of(2001, 8, 11))
                .gender(Gender.MALE)
                .country("Brasil")
                .state("Minas Gerais")
                .city("Belo Horizonte")
                .build();
    }

    public PersonResponseDTO responseDTO() {
        return new PersonResponseDTO(entity());
    }

}
