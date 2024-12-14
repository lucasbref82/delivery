package br.com.delivery.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;

@Entity
@Table(name = "COZINHAS")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Cozinha {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "NOME", length = 30)
    private String nome;

}
