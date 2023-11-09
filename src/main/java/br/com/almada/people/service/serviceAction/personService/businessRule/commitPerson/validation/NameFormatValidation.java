package br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.validation;

import br.com.almada.people.service.customException.InvalidNameFormatException;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.CommitPersonValidation;
import br.com.almada.people.service.serviceAction.personService.businessRule.commitPerson.CommitPersonValidationArgs;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
public class NameFormatValidation implements CommitPersonValidation {

    @Override
    public void validation(CommitPersonValidationArgs args) {
        String name = args.personDTO().name();

        String nameValidFormat = "^[a-zA-ZÀ-ú]+([ ][a-zA-ZÀ-ú]+)*$";

        if(!name.matches(nameValidFormat)) {
            throw new InvalidNameFormatException(name);
        }

    }

}
