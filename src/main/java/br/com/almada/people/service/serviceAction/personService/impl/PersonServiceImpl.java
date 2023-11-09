package br.com.almada.people.service.serviceAction.personService.impl;

import br.com.almada.people.dto.request.PersonRequestDTO;
import br.com.almada.people.dto.response.PersonResponseDTO;
import br.com.almada.people.entity.Person;
import br.com.almada.people.mapper.PersonMapper;
import br.com.almada.people.repository.PersonRepository;
import br.com.almada.people.repository.specification.PersonSpecification;
import br.com.almada.people.service.customException.ResourceNotFoundException;
import br.com.almada.people.service.serviceAction.logService.LogService;
import br.com.almada.people.service.serviceAction.personService.PersonService;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.CommitPersonValidation;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.CommitPersonValidationArgs;
import br.com.almada.people.service.serviceAction.sendEmailService.SendEmailPersonService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Primary
@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final LogService logService;

    private final SendEmailPersonService sendEmailPersonService;

    private final PersonMapper personMapper;

    private final List<CommitPersonValidation> commitPersonValidations;

    private final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Override
    public List<PersonResponseDTO> findAll(String name) {
        List<Person> people = personRepository.findAll(PersonSpecification.filter(name),
                                                       Sort.by(Sort.Direction.ASC, "name"));
        return personMapper.toDTOList(people);
    }

    @Override
    public PersonResponseDTO findById(String personId) {
        return personMapper.toDTO(findPersonDataBase(personId));
    }

    @Override
    public PersonResponseDTO save(PersonRequestDTO personRequestDTO) {

        commitPersonValidations.forEach(v -> v.validation(new CommitPersonValidationArgs(personRequestDTO, personRepository, null)));

        Person person = personMapper.toEntity(personRequestDTO);

        PersonResponseDTO personResponseDTO = savePersonAndConvertToDTO(person);

        logger.warn("Sending an e-mail in order to save a person on the database");
        sendEmailPersonService.sendEmailPersonCreated(person);

        logService.register(formatEventMessage("saved", personResponseDTO.getId(), personResponseDTO.getName()));

        return personResponseDTO;
    }

    @Override
    public PersonResponseDTO update(String personId, PersonRequestDTO personRequestDTO) {

        Person person = findPersonDataBase(personId);

        commitPersonValidations.forEach(v -> v.validation(new CommitPersonValidationArgs(personRequestDTO, personRepository, person)));

        personMapper.setAttributesToEntityToUpdate(personRequestDTO, person);

        PersonResponseDTO personResponseDTO = savePersonAndConvertToDTO(person);

        logger.warn("Sending an e-mail in order to save a person on the database");
        sendEmailPersonService.sendEmailPersonUpdated(person);

        logService.register(formatEventMessage("updated", personId, person.getName()));

        return personResponseDTO;
    }

    @Override
    public String remove(String personId) {
        Person person = findPersonDataBase(personId);
        personRepository.delete(person);
        logService.register(formatEventMessage("removed", personId, person.getName()));
        return person.getName();
    }

    private Person findPersonDataBase(String personId) {
        return personRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("The person id: " + personId + " wasn't found on database"));
    }

    private PersonResponseDTO savePersonAndConvertToDTO(Person person) {
        return personMapper.toDTO(personRepository.save(person));
    }

    private String formatEventMessage(String action, String personId, String personName) {
        return "Person " + action + " from database, Details: {id: " + personId + ", Name: " + personName+"}";
    }

}
