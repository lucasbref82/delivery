package br.com.delivery.v1.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "shipping_fee", precision = 10, scale = 2, nullable = false)
    private BigDecimal shippingFee;

    @ManyToOne
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    @Embedded
    private Address address;

    @ManyToMany
    @JoinTable(name = "restaurant_payment_method",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    @JsonIgnore
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

}
