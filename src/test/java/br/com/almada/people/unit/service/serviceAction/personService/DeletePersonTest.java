package br.com.almada.people.unit.service.serviceAction.personService;

import br.com.almada.people.entity.Person;
import br.com.almada.people.factory.PersonFactory;
import br.com.almada.people.repository.PersonRepository;
import br.com.almada.people.service.customException.ResourceNotFoundException;
import br.com.almada.people.service.serviceAction.logService.LogService;
import br.com.almada.people.service.serviceAction.personService.impl.PersonServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class DeletePersonTest {

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    private PersonFactory personFactory;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private LogService logService;


    @Test
    void deletePersonIdFound() {

        Person person = personFactory.entity();

        String validId = person.getId();

        when(personRepository.findById(validId)).thenReturn(Optional.of(person));

        Assertions.assertDoesNotThrow(() -> personService.remove(validId),
                "Não deve lançar exception");

        Mockito.verify(personRepository).findById(validId);
    }

    @Test
    void deletePersonIdNotFound() {

        String invalidId = "Id Invalid";

        when(personRepository.findById(invalidId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> personService.remove(invalidId),
                "Deve lançar exception pois não encontrou o usuário");

        Mockito.verify(personRepository).findById(invalidId);

    }

}
