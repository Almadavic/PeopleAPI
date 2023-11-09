package br.com.almada.people.unit.service.serviceAction.personService.businessRule.commitPerson;

import br.com.almada.people.dto.request.PersonRequestDTO;
import br.com.almada.people.service.customException.InvalidNameFormatException;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.CommitPersonValidationArgs;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.validation.NameFormatValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class NameFormatTest {

    @Autowired
    private NameFormatValidation nameFormatValidationService;

    @Test
    void invalidNameFormat() {

        PersonRequestDTO personDTO = createPersonDTOToTestNameValidation("Invalid@Name!");

        Assertions.assertThrows(InvalidNameFormatException.class,
                () -> nameFormatValidationService.validation(new CommitPersonValidationArgs(personDTO, null, null)),
                "Deve ser lançada uma exception pois o nome está inválido");

    }

    @Test
    void validNameFormat() {

        PersonRequestDTO personDTO = createPersonDTOToTestNameValidation("Valid name");

        Assertions.assertDoesNotThrow(() -> nameFormatValidationService.validation(new CommitPersonValidationArgs(personDTO, null, null)),
                "Não deve ser lançada uma exception pois o nome está válido");

    }

    private PersonRequestDTO createPersonDTOToTestNameValidation(String name) {
        return new PersonRequestDTO(name, null, null, null, null);
    }

}
