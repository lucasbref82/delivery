package br.com.delivery.domain.services;

import br.com.delivery.domain.entities.Cozinha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CozinhaService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cozinha> listarCozinhas() {
        return entityManager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

}
