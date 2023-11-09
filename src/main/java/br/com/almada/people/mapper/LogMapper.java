package br.com.almada.people.mapper;

import br.com.almada.people.dto.response.LogResponseDTO;
import br.com.almada.people.entity.Log;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class LogMapper {

    public LogResponseDTO toDTO(Log log) {
        return new LogResponseDTO(log);
    }

    public List<LogResponseDTO> toDTOList(List<Log> logs) {
        return logs.stream().map(this::toDTO).toList();
    }


}
