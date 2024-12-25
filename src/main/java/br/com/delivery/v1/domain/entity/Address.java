package br.com.delivery.v1.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class Address {

    @Column(name = "address_zip_code")
    private String zipCode;

    @Column(name = "address_public_place")
    private String publicPlace;

    @Column(name = "address_number")
    private String number;

    @Column(name = "address_complement")
    private String complement;

    @Column(name = "address_neighborhood")
    private String neighborhood;

    @ManyToOne
    @JoinColumn(name = "address_city_id")
    private City city;

}
