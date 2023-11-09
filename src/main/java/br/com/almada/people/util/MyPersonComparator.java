package br.com.almada.people.util;

import br.com.almada.people.entity.Person;

import java.util.Comparator;

public class MyPersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().compareTo(p2.getName());
    }

}
