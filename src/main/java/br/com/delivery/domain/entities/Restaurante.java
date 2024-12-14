package br.com.delivery.domain.entities;

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
@Table(name = "restaurantes")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Restaurante {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME", length = 30)
    private String nome;

    @Column(name = "TAXA_FRETE", precision = 10, scale = 2)
    private BigDecimal taxaFrete;

}
