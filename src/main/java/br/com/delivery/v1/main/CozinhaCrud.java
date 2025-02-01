package br.com.delivery.v1.main;

import br.com.delivery.v1.model.Cozinha;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CozinhaCrud {

    @PersistenceContext
    private EntityManager manager;

    public List<Cozinha> listar() {
        return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
    }

    @Transactional
    public void adicionarVarias(List<Cozinha> cozinhas) {
        cozinhas.forEach(cozinha -> manager.merge(cozinha));
    }

    @Transactional
    public Cozinha adicionar(Cozinha cozinha) {
        return manager.merge(cozinha);
    }

    public Cozinha buscar(Integer id) {
        return manager.find(Cozinha.class, id);
    }
}
