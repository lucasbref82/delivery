package br.com.delivery.v1.service;

import br.com.delivery.configs.SchedulersConfig;
import br.com.delivery.v1.domain.entity.Kitchen;
import br.com.delivery.v1.domain.exception.NotFoundException;
import br.com.delivery.v1.infrastructure.repositoryimpl.BaseRepositoryImpl;
import br.com.delivery.v1.infrastructure.repositoryimpl.KitchenRepositoryImpl;
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
public class KitchenService {

    @PersistenceContext
    private EntityManager entityManager;

    private final KitchenRepositoryImpl kitchenRepository;

    private final SchedulersConfig schedulersConfig;

    public List<Kitchen> findAll() {
        return Maybe.fromCallable(kitchenRepository::findAll)
                .subscribeOn(schedulersConfig.defaultScheduler())
                .filter(cozinhas -> !cozinhas.isEmpty())
                .switchIfEmpty(Maybe.just(List.of()))
                .blockingGet();
    }

    public Kitchen findById(Long id) {
        return Maybe.fromOptional(kitchenRepository.findById(id)).switchIfEmpty(Maybe.error(new NotFoundException("Kitchen of id {} not found."))).blockingGet();
    }

    @Transactional
    public Kitchen save(Kitchen kitchen) {
        return Maybe.fromOptional(kitchenRepository.save(kitchen)).blockingGet();
    }

    @Transactional
    public void saveAll(List<Kitchen> kitchens) {
        Observable.fromIterable(kitchens)
                .subscribeOn(schedulersConfig.defaultScheduler())
                .blockingSubscribe(kitchen -> entityManager.persist(kitchen));
    }

    @Transactional
    public void deleteKitchen(Long id) {
        var managedKitchen = findById(id);
        entityManager.remove(managedKitchen);
    }

}
