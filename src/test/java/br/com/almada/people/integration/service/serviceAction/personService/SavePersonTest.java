package br.com.almada.people.integration.service.serviceAction.personService;

import br.com.almada.people.dto.request.PersonRequestDTO;
import br.com.almada.people.dto.response.PersonResponseDTO;
import br.com.almada.people.entity.Log;
import br.com.almada.people.factory.PersonFactory;
import br.com.almada.people.repository.LogRepository;
import br.com.almada.people.repository.PersonRepository;
import br.com.almada.people.service.serviceAction.personService.impl.PersonServiceImpl;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.validation.EmailAvailableValidation;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.validation.EmailFormatValidation;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.validation.NameFormatValidation;
import br.com.almada.people.service.serviceAction.sendEmailService.SendEmailPersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SavePersonTest {

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private PersonFactory personFactory;

    @MockBean
    private SendEmailPersonService sendEmailService;

    @MockBean
    private NameFormatValidation nameFormatValidation;

    @MockBean
    private EmailFormatValidation emailFormatValidation;

    @MockBean
    private EmailAvailableValidation emailAvailableValidation;

    @Test
    void verifyPersonSavedAndLogCreated() {

        PersonRequestDTO personRequestDTO = personFactory.requestDTO();

        PersonResponseDTO personResponseDTO = personService.save(personRequestDTO);

        Assertions.assertDoesNotThrow(() -> personService.findById(personResponseDTO.getId()),
                "Não é para retornar exception pois o dado já foi salvo");

        List<Log> logs = logRepository.findAll();

        Assertions.assertTrue(logs.stream().anyMatch(l -> l.getEvent().contains(personResponseDTO.getId())
                && l.getEvent().contains(personResponseDTO.getName())
                && l.getEvent().contains("saved")));

        Assertions.assertEquals(personRequestDTO.gender(), personResponseDTO.getGender());
        Assertions.assertEquals(personRequestDTO.email(), personResponseDTO.getEmail());

        Assertions.assertTrue(personResponseDTO.getCreatedAt().isAfter(LocalDateTime.now().minusSeconds(60)) &&
                personResponseDTO.getCreatedAt().isBefore(LocalDateTime.now().plusSeconds(60)));

        logRepository.deleteAll();
        personRepository.deleteAll();

    }

}
