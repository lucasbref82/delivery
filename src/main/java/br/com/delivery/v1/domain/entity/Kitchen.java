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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Kitchen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", length = 30)
    private String name;

}
