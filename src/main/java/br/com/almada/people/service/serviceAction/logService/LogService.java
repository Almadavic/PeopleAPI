package br.com.almada.people.service.serviceAction.logService;

import br.com.almada.people.dto.response.LogResponseDTO;

import java.util.List;


public interface LogService {

    List<LogResponseDTO> findAll();

    void register(String event);

}
