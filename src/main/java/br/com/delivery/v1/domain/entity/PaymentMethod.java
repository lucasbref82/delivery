package br.com.delivery.v1.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "payment_method")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

}
