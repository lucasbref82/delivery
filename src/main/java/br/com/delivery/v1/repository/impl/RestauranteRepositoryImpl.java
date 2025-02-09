package br.com.delivery.v1.repository.impl;

import br.com.delivery.v1.model.Restaurante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> findByNomeAndTaxaFreteInicialAndTaxaFreteFinal(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        String jpql = "from Restaurante where nome like :nome and taxaFrete between :taxaFreteInicial and :taxaFreteFinal";
        return entityManager.createQuery(jpql)
                .setParameter("nome", "%" + nome + "%")
                .setParameter("taxaFreteInicial", taxaFreteInicial)
                .setParameter("taxaFreteFinal", taxaFreteFinal)
                .getResultList();
    }
}
