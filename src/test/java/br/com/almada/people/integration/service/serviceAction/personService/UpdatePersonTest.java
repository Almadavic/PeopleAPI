package br.com.almada.people.integration.service.serviceAction.personService;

import br.com.almada.people.dto.request.PersonRequestDTO;
import br.com.almada.people.entity.Log;
import br.com.almada.people.entity.Person;
import br.com.almada.people.factory.PersonFactory;
import br.com.almada.people.repository.LogRepository;
import br.com.almada.people.repository.PersonRepository;
import br.com.almada.people.service.serviceAction.personService.impl.PersonServiceImpl;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.validation.EmailAvailableValidation;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.validation.EmailFormatValidation;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.validation.NameFormatValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ActiveProfiles(value = "test")
@SpringBootTest
class UpdatePersonTest {

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private PersonFactory personFactory;

    @MockBean
    private NameFormatValidation nameFormatValidation;

    @MockBean
    private EmailFormatValidation emailFormatValidation;

    @MockBean
    private EmailAvailableValidation emailAvailableValidation;

    @Test
    void verifyPersonUpdatedAndLogCreated() {

         Person person = personFactory.entity();

         person =  personRepository.save(person);

         PersonRequestDTO personRequestDTO = personFactory.requestDTO2();

         personService.update(person.getId(), personRequestDTO);

         Person personDataBase = personRepository.findById(person.getId()).get();

         Assertions.assertEquals(personRequestDTO.email(), personDataBase.getEmail());

        List<Log> logs = logRepository.findAll();

        Assertions.assertTrue(logs.stream().anyMatch(l -> l.getEvent().contains(personDataBase.getId())
                && l.getEvent().contains(personDataBase.getName())
                && l.getEvent().contains("updated")));

        logRepository.deleteAll();
        personRepository.deleteAll();

    }

}
