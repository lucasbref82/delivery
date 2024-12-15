package br.com.delivery.v1.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "restaurants")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Restaurant {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 30)
    private String name;

    @Column(name = "SHIPPING FEE", precision = 10, scale = 2)
    private BigDecimal shippingFee;

}
