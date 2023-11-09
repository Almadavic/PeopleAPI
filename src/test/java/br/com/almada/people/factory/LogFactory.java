package br.com.almada.people.factory;

import br.com.almada.people.dto.response.LogResponseDTO;
import br.com.almada.people.entity.Log;
import org.springframework.stereotype.Component;

@Component
public class LogFactory {

    public Log entity() {
        return new Log("Usuário registrado");
    }

    public LogResponseDTO responseDTO() {
        return new LogResponseDTO(entity());
    }

}
