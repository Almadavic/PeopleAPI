package br.com.almada.people.unit.service.serviceAction.personService;

import br.com.almada.people.dto.request.PersonRequestDTO;
import br.com.almada.people.entity.Person;
import br.com.almada.people.factory.PersonFactory;
import br.com.almada.people.mapper.PersonMapper;
import br.com.almada.people.repository.PersonRepository;
import br.com.almada.people.service.customException.ResourceNotFoundException;
import br.com.almada.people.service.serviceAction.logService.LogService;
import br.com.almada.people.service.serviceAction.personService.impl.PersonServiceImpl;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.validation.EmailAvailableValidation;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.validation.EmailFormatValidation;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.validation.NameFormatValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UpdatePersonTest {

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    private PersonFactory personFactory;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private LogService logService;

    @MockBean
    private PersonMapper personMapper;

    @MockBean
    private NameFormatValidation nameFormatValidation;

    @MockBean
    private EmailFormatValidation emailFormatValidation;

    @MockBean
    private EmailAvailableValidation emailAvailableValidation;

    private PersonRequestDTO personRequestDTO;

    @BeforeEach
    void setUp() {
        this.personRequestDTO = personFactory.requestDTO();
    }


    @Test
    void updatePersonIdFound() {

        Person person = personFactory.entity();

        String validId = person.getId();

        when(personRepository.findById(validId)).thenReturn(Optional.of(person));
        when(personRepository.save(person)).thenReturn(person);

        Assertions.assertDoesNotThrow(() -> personService.update(validId, personRequestDTO),
                "Não deve lançar exception");

        Mockito.verify(personRepository).findById(validId);
        Mockito.verify(personRepository).save(person);

    }

    @Test
    void updatePersonIdNotFound() {

        String invalidId = "Id Invalid";

        when(personRepository.findById(invalidId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> personService.update(invalidId, personRequestDTO),
                "Deve lançar exception pois não encontrou o usuário");

        Mockito.verify(personRepository).findById(invalidId);

    }

}
