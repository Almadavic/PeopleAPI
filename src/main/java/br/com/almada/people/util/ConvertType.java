package br.com.almada.people.util;

import br.com.almada.people.enumerated.Gender;
import br.com.almada.people.service.customException.InvalidEnumValueException;

import java.util.Arrays;

public class ConvertType {

    private ConvertType() {

    }

    public static Gender convertFromStringToEnum(String value) {

        try {
            return Gender.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumValueException(value, "Gender", ListEnumValues.returnEnumValues(Arrays.asList(Gender.values())));
        }

    }

}
