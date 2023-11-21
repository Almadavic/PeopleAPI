package br.com.almada.people.service.serviceAction.logService.impl;

import br.com.almada.people.dto.response.LogResponseDTO;
import br.com.almada.people.mapper.LogMapper;
import br.com.almada.people.service.serviceAction.logService.LogService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

//@Primary
@Service
@RequiredArgsConstructor
public class LogServiceEntityManagerImpl implements LogService {


    @PersistenceContext
    private EntityManager entityManager;

    private final LogMapper logMapper;

    @Override
    public List<LogResponseDTO> findAll() {

        Long id = 341L;

        StringBuilder jpql = new StringBuilder("SELECT l from Log l WHERE 1 = 1");

        if(id!= null) {
            jpql.append("AND l.id > :id");
        }

        Query query = entityManager.createQuery(jpql.toString());

        if(id != null) {
            query.setParameter("id", id);
        }

        return logMapper.toDTOList(query.getResultList());
    }

    @Override
    public void register(String event) {
      //  logRepository.save(new Log(event));
    }

}
