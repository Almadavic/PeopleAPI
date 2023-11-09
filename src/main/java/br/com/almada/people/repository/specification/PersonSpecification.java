package br.com.almada.people.repository.specification;

import br.com.almada.people.entity.Person;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;



public class PersonSpecification {

    private PersonSpecification() {

    }

    public static Specification<Person> filter (String name) {

        return (root, query, criteriaBuilder) -> {

            Predicate predicate =  criteriaBuilder.conjunction();

            if(name != null) {

                predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        name.toLowerCase() + "%"));

            }


            return predicate;
        };

    }

}
