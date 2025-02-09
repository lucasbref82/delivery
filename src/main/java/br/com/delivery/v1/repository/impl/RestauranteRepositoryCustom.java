package br.com.delivery.v1.repository.impl;

import br.com.delivery.v1.model.Restaurante;

import java.math.BigDecimal;
import java.util.List;

public interface RestauranteRepositoryCustom {
    List<Restaurante> findByNomeAndTaxaFreteInicialAndTaxaFreteFinal(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
}
