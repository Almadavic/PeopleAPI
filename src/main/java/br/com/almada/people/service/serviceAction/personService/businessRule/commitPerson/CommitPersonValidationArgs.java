package br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson;

import br.com.almada.people.dto.request.PersonRequestDTO;
import br.com.almada.people.entity.Person;
import br.com.almada.people.repository.PersonRepository;

public record CommitPersonValidationArgs(PersonRequestDTO personDTO, PersonRepository personRepository, Person person) {
}
