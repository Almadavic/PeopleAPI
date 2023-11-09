package br.com.almada.people.controller;

import br.com.almada.people.config.swagger.endPoint.AdminActionSwagger;
import br.com.almada.people.dto.response.StatusResponseDTO;
import br.com.almada.people.service.serviceAction.adminActionService.AdminActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/admin")
@RequiredArgsConstructor
public class AdminActionController implements AdminActionSwagger {

    private final AdminActionService adminActionService;

    @Override
    @GetMapping(value = "/backup/{password}")
    public ResponseEntity<StatusResponseDTO> backup(@PathVariable(value = "password") String password) {
        adminActionService.backup(password);
        return ResponseEntity.ok().body(new StatusResponseDTO("Backup performed successfully"));
    }

}
