package br.com.almada.people.service.serviceAction.adminActionService;

import br.com.almada.people.entity.Person;
import br.com.almada.people.enumerated.Gender;
import br.com.almada.people.repository.LogRepository;
import br.com.almada.people.repository.PersonRepository;
import br.com.almada.people.service.customException.InvalidPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AdminActionService {

    private final PersonRepository personRepository;

    private final LogRepository logRepository;

    @Value(value = "${backup.password}")
    private String backupPassword;

    public void backup(String password) {

        if (!password.equals(backupPassword)) {
            throw new InvalidPasswordException();
        }

        deleteAllPeople();
        addPeople();
        deleteAllLogs();
        addLogs();

    }

    private void deleteAllPeople() {

        personRepository.deleteAll();

    }

    private void deleteAllLogs() {

        logRepository.deleteAll();

    }

    private void addPeople() {

        String country = "Brasil";
        String state = "Minas Gerais";
        String city = "Belo Horizonte";

        personRepository.saveAll(
                Arrays.asList(
                        Person.builder()
                                .name("Victor Almada")
                                .email("almadavicc@hotmail.com")
                                .dateOfBirth(LocalDate.of(2001, 8, 11))
                                .gender(Gender.MALE)
                                .country(country)
                                .state(state)
                                .city(city)
                                .build(),

                        Person.builder()
                                .name("Euni Lina")
                                .email("eunilina@hotmail.com")
                                .dateOfBirth(LocalDate.of(1944, 10, 8))
                                .gender(Gender.FEMALE)
                                .country(country)
                                .state(state)
                                .city(city)
                                .build(),

                        Person.builder()
                                .name("Renato Tavares")
                                .email("renatota@gmail.com")
                                .dateOfBirth(LocalDate.of(1976, 11, 16))
                                .gender(Gender.MALE)
                                .country(country)
                                .state(state)
                                .city(city)
                                .build(),

                        Person.builder()
                                .name("Adriana Almada")
                                .email("dri-almaa@hotmail.com")
                                .dateOfBirth(LocalDate.of(1972, 9, 3))
                                .gender(Gender.FEMALE)
                                .country(country)
                                .state(state)
                                .city(city)
                                .build(),

                        Person.builder()
                                .name("Larissa")
                                .email("lalahnunes@gmail.com")
                                .dateOfBirth(LocalDate.of(2005, 5, 27))
                                .gender(Gender.FEMALE)
                                .country(country)
                                .state(state)
                                .city(city)
                                .build()
                ));

    }

    private void addLogs() {

//        logRepository.saveAll(
//                Arrays.asList(
//                        new Log("Usuário 1 inserido no banco de dados"),
//                        new Log("Usuário 2 inserido no banco de dados"),
//                        new Log("Usuário 3 inserido no banco de dados"),
//                        new Log("Usuário 4 inserido no banco de dados"),
//                        new Log("Usuário 5 inserido no banco de dados")
//
//        ));

    }

}
