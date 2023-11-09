package br.com.almada.people.service.serviceAction.personService;

import br.com.almada.people.dto.request.PersonRequestDTO;
import br.com.almada.people.dto.response.PersonResponseDTO;

import java.util.List;

public interface PersonService {

    List<PersonResponseDTO> findAll(String name);

    PersonResponseDTO findById(String personId);

   PersonResponseDTO save(PersonRequestDTO personRequestDTO);

    PersonResponseDTO update(String personId, PersonRequestDTO personRequestDTO);

    String remove(String personId);

}
