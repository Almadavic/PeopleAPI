package br.com.almada.people.controller;

import br.com.almada.people.config.swagger.endPoint.LogSwagger;
import br.com.almada.people.dto.response.StatusDataCollectionLinksResponseDTO;
import br.com.almada.people.dto.response.LogResponseDTO;
import br.com.almada.people.service.serviceAction.logService.LogService;
import br.com.almada.people.util.HandleResponseStatusWithList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/logs")
@RequiredArgsConstructor
public class LogController implements LogSwagger {

    private final LogService logService;

    @Override
    @GetMapping
    public ResponseEntity<StatusDataCollectionLinksResponseDTO<List<LogResponseDTO>>> findAll() {
        return ResponseEntity.ok().body(handleLogsCollectionJsonFormat(logService.findAll()));
    }

    private StatusDataCollectionLinksResponseDTO<List<LogResponseDTO>> handleLogsCollectionJsonFormat(List<LogResponseDTO> logs) {
        StatusDataCollectionLinksResponseDTO<List<LogResponseDTO>> json =
                new StatusDataCollectionLinksResponseDTO<>(
                        HandleResponseStatusWithList.message(logs, "Logs"),
                        logs);

        json.addLink("self", "logs", "get");

        return json;
    }

}
