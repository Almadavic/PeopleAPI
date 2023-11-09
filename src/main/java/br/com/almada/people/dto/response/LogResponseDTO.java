package br.com.almada.people.dto.response;

import br.com.almada.people.entity.Log;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonPropertyOrder(value = {"id", "event", "event_time"})
@Getter
public class LogResponseDTO {

    @JsonProperty(value = "id")
    private final Long id;

    @JsonProperty(value = "event")
    private final String event;

    @JsonProperty(value = "event_time")
    private final LocalDateTime eventTime;

    public LogResponseDTO(Log log) {
        this.id = log.getId();
        this.event = log.getEvent();
        this.eventTime = log.getEventTime();
    }

}
