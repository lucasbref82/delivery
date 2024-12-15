package br.com.delivery.domain.services;

import br.com.delivery.configs.SchedulersConfig;
import br.com.delivery.domain.entities.Cozinha;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CozinhaService {

    @PersistenceContext
    private EntityManager entityManager;

    private final SchedulersConfig schedulersConfig;

    public List<Cozinha> listarCozinhas() {
        return Maybe.fromCallable(() -> entityManager.createQuery("from Cozinha", Cozinha.class).getResultList())
                .subscribeOn(schedulersConfig.defaultScheduler())
                .filter(cozinhas -> !cozinhas.isEmpty())
                .switchIfEmpty(Maybe.just(List.of()))
                .blockingGet();
    }

    public Cozinha buscar(Long id) {
        return Maybe.fromCallable(() -> entityManager.find(Cozinha.class, id)).blockingGet();
    }

    @Transactional
    public Cozinha salvarCozinha(Cozinha cozinha) {
        return Maybe.just(entityManager.merge(cozinha)).blockingGet();
    }

    @Transactional
    public void salvarTodasCozinhas(List<Cozinha> cozinhas) {
        Observable.fromIterable(cozinhas)
                .subscribeOn(schedulersConfig.defaultScheduler())
                .blockingSubscribe(cozinha -> entityManager.persist(cozinha));
    }

    @Transactional
    public void exclusaoCozinha(Cozinha cozinha) {
        var cozinhaAux = buscar(cozinha.getId());
        entityManager.remove(cozinhaAux);
    }

}
