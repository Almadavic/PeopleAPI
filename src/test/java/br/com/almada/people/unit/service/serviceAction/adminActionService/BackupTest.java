package br.com.almada.people.unit.service.serviceAction.adminActionService;

import br.com.almada.people.repository.PersonRepository;
import br.com.almada.people.service.customException.InvalidPasswordException;
import br.com.almada.people.service.serviceAction.adminActionService.AdminActionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BackupTest {

    @Autowired
    private AdminActionService adminActionService;

    @MockBean
    private PersonRepository personRepository;

    @Test
    void validPassword() {

        Assertions.assertDoesNotThrow(() -> adminActionService.backup("123456"),
                "Não deve lançar exception pois a senha está correta");

    }

    @Test
    void invalidPassword() {

        Assertions.assertThrows(InvalidPasswordException.class,
                () -> adminActionService.backup("1234567"),
                "Deve lançar exception pois a senha não está correta");

    }



}
