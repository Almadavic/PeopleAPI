package br.com.almada.people.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address implements Serializable {

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "city", nullable = false)
    private String city;

    @Builder
    public Address(String country, String state, String city) {
        this.country = country;
        this.state = state;
        this.city = city;
    }

}
