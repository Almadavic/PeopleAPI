package br.com.almada.people.mapper;

import br.com.almada.people.controller.PersonController;
import br.com.almada.people.dto.request.AddressRequestDTO;
import br.com.almada.people.dto.request.PersonRequestDTO;
import br.com.almada.people.dto.response.PersonResponseDTO;
import br.com.almada.people.entity.Person;
import br.com.almada.people.util.ConvertType;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PersonMapper {

    public Person toEntity(PersonRequestDTO personDTO) {
        AddressRequestDTO address = personDTO.address();
        return Person.builder()
                .name(personDTO.name())
                .email(personDTO.email())
                .dateOfBirth(personDTO.dateOfBirth())
                .gender(ConvertType.convertFromStringToEnum(personDTO.gender()))
                .country(address.country())
                .state(address.state())
                .city(address.city())
                .build();
    }

    public PersonResponseDTO toDTO(Person person) {
        PersonResponseDTO personDTO = new PersonResponseDTO(person);
        addHateoas(personDTO);
        personDTO.add(linkTo(methodOn(PersonController.class).findAll(null)).withRel(IanaLinkRelations.COLLECTION).withType("GET"));
        return personDTO;
    }

    public List<PersonResponseDTO> toDTOList(List<Person> people) {
        List<PersonResponseDTO> peopleDTOList = people.stream().map(PersonResponseDTO::new).toList();
        peopleDTOList.forEach(this::addHateoas);
        return peopleDTOList;
    }

    public void setAttributesToEntityToUpdate(PersonRequestDTO personDTO, Person person) {
        AddressRequestDTO address = personDTO.address();
        person.setName(personDTO.name());
        person.setEmail(personDTO.email());
        person.setDateOfBirth(personDTO.dateOfBirth());
        person.setGender(ConvertType.convertFromStringToEnum(personDTO.gender()));
        person.getAddress().setCountry(address.country());
        person.getAddress().setState(address.state());
        person.getAddress().setCity(address.city());
        person.setUpdatedAt(LocalDateTime.now());
    }

    private void addHateoas(PersonResponseDTO personDTO) {
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getId())).withSelfRel().withType("GET"));
        personDTO.add(linkTo(methodOn(PersonController.class).update(personDTO.getId(), null)).withRel("update").withType("PUT"));
        personDTO.add(linkTo(methodOn(PersonController.class).remove(personDTO.getId())).withRel("remove").withType("DELETE"));
    }



}
