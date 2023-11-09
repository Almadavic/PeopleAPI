package br.com.almada.people.unit.service.serviceAction.logService;

import br.com.almada.people.factory.LogFactory;
import br.com.almada.people.mapper.LogMapper;
import br.com.almada.people.repository.LogRepository;
import br.com.almada.people.service.serviceAction.logService.LogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ActiveProfiles(value = "test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class FindLogTest {

    @Autowired
    private LogService logService;

    @Autowired
    private LogFactory logFactory;

    @MockBean
    private LogRepository logRepository;

    @MockBean
    private LogMapper logMapper;

    @Test
    void findAll() {


        var logList = Collections.singletonList(logFactory.entity());

        when(logRepository.findAll()).thenReturn(logList);
        when(logMapper.toDTOList(logList)).thenReturn(Collections.singletonList(logFactory.responseDTO()));

        Assertions.assertDoesNotThrow(() -> logService.findAll(), "Não deve lançar exception");
        Assertions.assertEquals(1, logService.findAll().size(), "Tamanho da lista deve ser igual a 1");

        Mockito.verify(logRepository, times(2)).findAll();
        Mockito.verify(logMapper, times(2)).toDTOList(logList);

    }

}
