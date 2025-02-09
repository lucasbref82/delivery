package br.com.delivery.v1.repository;

import br.com.delivery.v1.model.Restaurante;
import br.com.delivery.v1.repository.impl.RestauranteRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer>, RestauranteRepositoryCustom {
    List<Restaurante> findByNomeAndTaxaFreteInicialAndTaxaFreteFinal(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
}
