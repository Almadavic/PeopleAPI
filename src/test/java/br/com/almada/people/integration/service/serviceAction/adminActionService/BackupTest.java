package br.com.almada.people.integration.service.serviceAction.adminActionService;

import br.com.almada.people.repository.LogRepository;
import br.com.almada.people.repository.PersonRepository;
import br.com.almada.people.service.serviceAction.adminActionService.AdminActionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class BackupTest {

    @Autowired
    private AdminActionService adminActionService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private LogRepository logRepository;

    @Test
    void verifyBackup() {

        adminActionService.backup("123456");

        Assertions.assertEquals(5, personRepository.findAll().size());

    }

}
