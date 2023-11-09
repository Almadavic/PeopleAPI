package br.com.almada.people.unit.service.serviceAction.personService;

import br.com.almada.people.entity.Person;
import br.com.almada.people.factory.PersonFactory;
import br.com.almada.people.mapper.PersonMapper;
import br.com.almada.people.repository.PersonRepository;
import br.com.almada.people.service.customException.ResourceNotFoundException;
import br.com.almada.people.service.serviceAction.personService.impl.PersonServiceImpl;
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

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class FindPersonTest {

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    private PersonFactory personFactory;

    private Person person;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private PersonMapper personMapper;

    @BeforeEach
    void setUp() {
        this.person = personFactory.entity();
    }

    @Test
    void findAll() {

        var peopleList = Collections.singletonList(person);

        when(personRepository.findAll()).thenReturn(peopleList);
        when(personMapper.toDTOList(peopleList)).thenReturn(Collections.singletonList(personFactory.responseDTO()));

        Assertions.assertDoesNotThrow(() -> personService.findAll(),
                "Não deve lançar exception");

        Assertions.assertEquals(1, personService.findAll().size(),
                "Tamanho da lista deve ser igual a 1");

        Mockito.verify(personRepository, times(2)).findAll();
        Mockito.verify(personMapper, times(2)).toDTOList(peopleList);

    }

    @Test
    void findByIdFound() {

        String validId = person.getId();

        when(personRepository.findById(validId)).thenReturn(Optional.of(person));

        Assertions.assertDoesNotThrow(() -> personService.findById(validId),
                "Não deve lançar exception");

        Mockito.verify(personRepository).findById(validId);

    }

    @Test
    void findByIdNotFound() {

        String invalidId = "Id Invalid";

        when(personRepository.findById(invalidId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> personService.findById(invalidId),
                "Deve lançar exception pois não encontrou o usuário");

        Mockito.verify(personRepository).findById(invalidId);

    }

}
