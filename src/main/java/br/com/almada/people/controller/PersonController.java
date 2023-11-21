package br.com.almada.people.controller;

import br.com.almada.people.config.swagger.endPoint.PersonSwagger;
import br.com.almada.people.dto.request.PersonRequestDTO;
import br.com.almada.people.dto.response.StatusDataCollectionLinksResponseDTO;
import br.com.almada.people.dto.response.StatusDataResponseDTO;
import br.com.almada.people.dto.response.PersonResponseDTO;
import br.com.almada.people.dto.response.StatusResponseDTO;
import br.com.almada.people.service.serviceAction.personService.impl.PersonServiceImpl;
import br.com.almada.people.util.HandleResponseStatusWithList;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/people")
@RequiredArgsConstructor
public class PersonController implements PersonSwagger {

    private final PersonServiceImpl personService;

    @Override
    @GetMapping
    public ResponseEntity<StatusDataCollectionLinksResponseDTO<List<PersonResponseDTO>>> findAll(@RequestParam(value = "name", required = false) String name){
        return ResponseEntity.ok().body(handlePeopleCollectionJsonFormat(personService.findAll(name)));
    }

    @Override
    @GetMapping(value = "/{personId}")
    public ResponseEntity<StatusDataResponseDTO<PersonResponseDTO>> findById(@PathVariable(value = "personId") String personId) {
        return ResponseEntity.ok().body(new StatusDataResponseDTO<>("Person recovered successfully", personService.findById(personId)));
    }

    @Override
    @PostMapping
    public ResponseEntity<StatusDataResponseDTO<PersonResponseDTO>> save(@RequestBody @Valid PersonRequestDTO personRequestDTO, UriComponentsBuilder uriBuilder) {

        PersonResponseDTO personResponseDTO = personService.save(personRequestDTO);

        URI uri = uriBuilder.path("/people/{id}").buildAndExpand(personResponseDTO.getId()).toUri();

        return ResponseEntity.created(uri).body(new StatusDataResponseDTO<>("Person " + personResponseDTO.getName() + " saved successfully", personResponseDTO));
    }

    @Override
    @PutMapping(value = "/{personId}")
    public ResponseEntity<StatusDataResponseDTO<PersonResponseDTO>> update(@PathVariable(value = "personId") String personId, @RequestBody @Valid PersonRequestDTO personRequestDTO) {
        PersonResponseDTO personResponseDTO = personService.update(personId, personRequestDTO);
        return ResponseEntity.ok().body(new StatusDataResponseDTO<>("Person " + personResponseDTO.getName() + " updated successfully!", personResponseDTO));
    }

    @Override
    @DeleteMapping(value = "/{personId}")
    public ResponseEntity<StatusResponseDTO> remove(@PathVariable(value = "personId") String personId) {
        String namePersonRemoved = personService.remove(personId);
        return ResponseEntity.ok().body(new StatusResponseDTO("Person " + namePersonRemoved  + " deleted successfully!"));
    }

    private StatusDataCollectionLinksResponseDTO<List<PersonResponseDTO>> handlePeopleCollectionJsonFormat(List<PersonResponseDTO> people) {
        StatusDataCollectionLinksResponseDTO<List<PersonResponseDTO>> json =
                new StatusDataCollectionLinksResponseDTO<>(
                        HandleResponseStatusWithList.message(people, "People"),
                        people);

        json.addLink("self", "people", "get");
        json.addLink("save", "people", "post");

        return json;
    }

}
