package br.com.almada.people.integration.service.serviceAction.sendEmailService;

import br.com.almada.people.entity.Person;
import br.com.almada.people.factory.PersonFactory;
import br.com.almada.people.service.serviceAction.sendEmailService.SendEmailPersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class SendEmailTest {

    @Autowired
    private SendEmailPersonService sendEmailService;

    @Autowired
    private PersonFactory personFactory;

    @Test
    void sendEmailSavePerson() {

        Person person = personFactory.entity();

        Assertions.assertDoesNotThrow(() -> sendEmailService.sendEmailPersonCreated(person),
                "E-mail deve ser enviado com sucesso");

    }

    @Test
    void sendEmailUpdatedPerson() {

        Person person = personFactory.entity();

        Assertions.assertDoesNotThrow(() -> sendEmailService.sendEmailPersonUpdated(person),
                "E-mail deve ser enviado com sucesso");

    }

}
