package br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.validation;

import br.com.almada.people.service.customException.EmailAlreadyRegisteredException;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.CommitPersonValidation;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.CommitPersonValidationArgs;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 3)
@Component
public class EmailAvailableValidation implements CommitPersonValidation {

    @Override
    public void validation(CommitPersonValidationArgs args) {

        String emailDTO = args.personDTO().email();

        // SAVE
        if(args.personRepository().findByEmail(emailDTO).isPresent() && args.person() == null) {
            throw new EmailAlreadyRegisteredException("The e-mail is already registered on the database");
        }

        // UPDATE
        if(args.personRepository().findByEmail(emailDTO).isPresent() && !emailDTO.equals(args.person().getEmail())) {
            throw new EmailAlreadyRegisteredException("The e-mail is already registered on the database");
        }

    }

}
