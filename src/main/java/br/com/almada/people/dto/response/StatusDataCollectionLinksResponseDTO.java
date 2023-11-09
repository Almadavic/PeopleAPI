package br.com.almada.people.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder(value = {"status", "root_link", "items_amount", "data"})
@Getter
public class StatusDataCollectionLinksResponseDTO<T> extends StatusDataResponseDTO<T> {

    @JsonProperty(value = "items_amount")
    private final Integer itemsAmount;

    @JsonProperty(value = "root_link")
    private final List<CollectionLinkResponseDTO> links = new ArrayList<>();

    public StatusDataCollectionLinksResponseDTO(String message, T data) {
        super(message, data);
        List<T> collection = (List<T>) data;
        this.itemsAmount = collection.size();
    }

    public void addLink(String rel, String path, String type) {
        this.links.add(new CollectionLinkResponseDTO(rel, path, type));
    }

}
