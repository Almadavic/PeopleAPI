package br.com.almada.people.integration.service.serviceAction.personService;

import br.com.almada.people.entity.Log;
import br.com.almada.people.entity.Person;
import br.com.almada.people.repository.LogRepository;
import br.com.almada.people.repository.PersonRepository;
import br.com.almada.people.service.customException.ResourceNotFoundException;
import br.com.almada.people.service.serviceAction.personService.impl.PersonServiceImpl;
import br.com.almada.people.factory.PersonFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles(value = "test")
@SpringBootTest
class DeletePersonTest {

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private PersonFactory personFactory;

    @Test
    void verifyPersonDeletedAndLogCreated() {

        Person person = personFactory.entity();

        personRepository.save(person);

        personService.remove(person.getId());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> personService.findById(person.getId()),
                "É para retornar exception pois o dado não foi encontrado");

        List<Log> logs = logRepository.findAll();

        Assertions.assertTrue(logs.stream().anyMatch(l -> l.getEvent().contains(person.getId())
        && l.getEvent().contains(person.getName())
        && l.getEvent().contains("removed")));

        logRepository.deleteAll();

    }

}
