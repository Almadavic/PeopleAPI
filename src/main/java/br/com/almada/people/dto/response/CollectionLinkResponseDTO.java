package br.com.almada.people.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

@JsonPropertyOrder(value = {"rel", "href", "type"})
@Getter
public class CollectionLinkResponseDTO {

    @JsonProperty(value = "rel")
    private final String rel;

    @JsonProperty(value = "href")
    private final String href;

    @JsonProperty(value = "type")
    private final String type;

    public CollectionLinkResponseDTO(String rel, String path, String type) {
        this.rel = rel;
        this.href = "http://localhost:8080/api/".concat(path);
        this.type = type.toUpperCase();
    }

}
