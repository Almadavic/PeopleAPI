package br.com.almada.people.unit.service.serviceAction.personService.businessRule.commitPerson;

import br.com.almada.people.dto.request.PersonRequestDTO;
import br.com.almada.people.entity.Person;
import br.com.almada.people.factory.PersonFactory;
import br.com.almada.people.repository.PersonRepository;
import br.com.almada.people.service.customException.EmailAlreadyRegisteredException;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.CommitPersonValidationArgs;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.validation.EmailAvailableValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class EmailAvailableTest {

    @Autowired
    private EmailAvailableValidation emailAvailableValidationService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonFactory personFactory;

    @Test
    void emailAvailableSave() {

        String availableEmail = "almadavic@hotmail.com";

        PersonRequestDTO personDTO = createPersonDTOToTestEmailValidation(availableEmail);

        Assertions.assertDoesNotThrow(() -> emailAvailableValidationService.validation(new CommitPersonValidationArgs(personDTO, personRepository, null)));

    }

    @Test
    void emailAvailableUpdate() {

        String availableEmail = "almadavic@live.com";

        Person person = personFactory.entity();

        PersonRequestDTO personDTO = createPersonDTOToTestEmailValidation(availableEmail);

        Assertions.assertDoesNotThrow(() -> emailAvailableValidationService.validation(new CommitPersonValidationArgs(personDTO, personRepository, person)));

    }

    @Test
    void emailUnavailableSavePerson() {

        Person person = personFactory.entity();

        personRepository.save(person);

        String unavailableEmail = "almadavic@live.com";

        PersonRequestDTO personDTO = createPersonDTOToTestEmailValidation(unavailableEmail);

        Assertions.assertThrows(EmailAlreadyRegisteredException.class,
                () -> emailAvailableValidationService.validation(new CommitPersonValidationArgs(personDTO, personRepository, null)),
                "Deve lançar exception pois o e-mail já existe no banco de dados");

        personRepository.deleteAll();

    }

    private PersonRequestDTO createPersonDTOToTestEmailValidation(String email) {
        return new PersonRequestDTO(null, email, null, null, null);
    }

}
