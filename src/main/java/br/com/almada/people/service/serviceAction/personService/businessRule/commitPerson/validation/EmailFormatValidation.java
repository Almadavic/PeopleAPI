package br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.validation;

import br.com.almada.people.service.customException.InvalidEmailFormatException;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.CommitPersonValidation;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.CommitPersonValidationArgs;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 2)
@Component
public class EmailFormatValidation implements CommitPersonValidation {

    @Override
    public void validation(CommitPersonValidationArgs args) {

        String email = args.personDTO().email();

        String emailValidFormat = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if(!email.matches(emailValidFormat)) {
            throw new InvalidEmailFormatException(email);
        }

    }

}
