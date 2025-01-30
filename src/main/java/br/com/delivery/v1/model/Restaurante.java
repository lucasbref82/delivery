package br.com.delivery.v1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@Entity
@Table(name = "restaurante")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

    @Id
    @Column
    @EqualsAndHashCode.Include
    private Integer id;

    @Column
    private String nome;

    @Column
    private BigDecimal taxaFrete;
}
