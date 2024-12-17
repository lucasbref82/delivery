package br.com.delivery.v1.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "data_request", schema = "dbo")
public class DataRequest {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // bigint

    @Column(name = "request_date", nullable = false)
    private LocalDateTime dateRequest; // datetime2

    @Column(name = "uri", nullable = false, length = 8000)
    private String uri; // varchar(8000)

    @Column(name = "request_body", nullable = true, length = 2147483647)
    private String requestBody; // varchar(max)

    @Column(name = "method", nullable = false, length = 10)
    private String method; // varchar(10)

    @Column(name = "headers", nullable = true, length = 2147483647)
    private String headers; // varchar(max)

    @Column(name = "responde_date", nullable = true)
    private LocalDateTime dataResponse; // datetime2

    @Column(name = "status_code", nullable = true)
    private Integer statusCode; // int

    @Column(name = "response_body", nullable = true, length = 2147483647)
    private String bodyResponse; // varchar(max)

    @Column(name = "duration")
    private BigDecimal duration; // numeric(17,6)
}
