package br.com.delivery.v1.main;

import br.com.delivery.v1.model.Cozinha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CozinhaCrud {

    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> listarCozinhas() {
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }
}
