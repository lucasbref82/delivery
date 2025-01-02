package br.com.delivery.v1.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "shipping_fee", precision = 10, scale = 2, nullable = false)
    private BigDecimal shippingFee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;

    @Embedded
    private Address address;

    @CreationTimestamp
    @Column(name = "registration_date", nullable = false, columnDefinition = "datetime")
    private LocalDateTime resgistrationDate;

    @UpdateTimestamp
    @Column(name = "change_date", nullable = false, columnDefinition = "datetime")
    private LocalDateTime changeDate;

    @OneToMany(mappedBy = "restaurant")
    @Builder.Default
    private List<Product> products = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "restaurant_payment_method",
            joinColumns = @JoinColumn(name = "restaurant_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_method_id"))
    @Builder.Default
    private List<PaymentMethod> paymentMethods = new ArrayList<>();

}
