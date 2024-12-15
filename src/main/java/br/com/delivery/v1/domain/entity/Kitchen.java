package br.com.delivery.v1.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kitchens")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Kitchen {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME", length = 30)
    private String name;

}
