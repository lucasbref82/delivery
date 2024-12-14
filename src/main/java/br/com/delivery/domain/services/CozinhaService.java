package br.com.delivery.domain.services;

import br.com.delivery.domain.entities.Cozinha;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CozinhaService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Cozinha> listarCozinhas() {
        return Maybe.fromCallable(() -> entityManager.createQuery("from Cozinha", Cozinha.class).getResultList())
                .subscribeOn(Schedulers.io())
                .filter(cozinhas -> !cozinhas.isEmpty())
                .switchIfEmpty(Maybe.just(List.of()))
                .blockingGet();
    }

    public Cozinha salvarCozinha(Cozinha cozinha) {
        return entityManager.merge(cozinha);
    }

    @Transactional
    public void salvarTodasCozinhas(List<Cozinha> cozinhas) {
        Observable.fromIterable(cozinhas)
                .subscribeOn(Schedulers.io())
                .blockingSubscribe(cozinha -> entityManager.persist(cozinha));
    }

}
