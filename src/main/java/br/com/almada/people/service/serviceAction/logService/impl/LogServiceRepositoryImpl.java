package br.com.almada.people.service.serviceAction.logService.impl;

import br.com.almada.people.dto.response.LogResponseDTO;
import br.com.almada.people.entity.Log;
import br.com.almada.people.mapper.LogMapper;
import br.com.almada.people.repository.LogRepository;
import br.com.almada.people.service.serviceAction.logService.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

//@Primary
@Service
@RequiredArgsConstructor
public class LogServiceRepositoryImpl implements LogService {

    private final LogRepository logRepository;

    private final LogMapper logMapper;

    @Override
    public List<LogResponseDTO> findAll() {
        return logMapper.toDTOList(logRepository.findAll(
                Sort.by(Sort.Direction.DESC, "eventTime")
        ));
    }

    @Override
    public void register(String event) {
        logRepository.save(new Log(event));
    }

}
