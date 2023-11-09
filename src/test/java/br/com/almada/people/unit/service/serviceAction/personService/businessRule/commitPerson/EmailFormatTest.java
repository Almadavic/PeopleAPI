package br.com.almada.people.unit.service.serviceAction.personService.businessRule.commitPerson;

import br.com.almada.people.dto.request.PersonRequestDTO;
import br.com.almada.people.service.customException.InvalidEmailFormatException;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.CommitPersonValidationArgs;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.validation.EmailFormatValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class EmailFormatTest {

    @Autowired
    private EmailFormatValidation emailFormatValidationService;

    @Test
    void invalidEmailFormat() {

        PersonRequestDTO personDTO = createPersonDTOToTestEmailValidation("almadavic@live.com!");

        Assertions.assertThrows(InvalidEmailFormatException.class,
                () -> emailFormatValidationService.validation(new CommitPersonValidationArgs(personDTO, null, null)),
                "Deve ser lançada uma exception pois o e-mail está inválido");

    }

    @Test
    void validEmailFormat() {

        PersonRequestDTO personDTO = createPersonDTOToTestEmailValidation("almadavic@live.com");

        Assertions.assertDoesNotThrow(() -> emailFormatValidationService.validation(new CommitPersonValidationArgs(personDTO, null, null)),
                "Não deve ser lançada uma exception pois o e-mail está válido");

    }

    private PersonRequestDTO createPersonDTOToTestEmailValidation(String email) {
        return new PersonRequestDTO(null, email, null, null, null);
    }

}
