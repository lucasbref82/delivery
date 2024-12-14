package br.com.delivery.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cozinhas")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cozinha {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", length = 30)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
