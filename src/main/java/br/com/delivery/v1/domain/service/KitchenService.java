package br.com.delivery.v1.domain.service;

import br.com.delivery.configs.SchedulersConfig;
import br.com.delivery.utils.Utils;
import br.com.delivery.v1.domain.entity.Kitchen;
import br.com.delivery.v1.domain.exception.NotFoundException;
import br.com.delivery.v1.domain.repository.KitchenRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KitchenService {

    private final KitchenRepository kitchenRepository;
    private final SchedulersConfig schedulersConfig;

    public List<Kitchen> findAll() {
        return Maybe.fromCallable(kitchenRepository::findAll)
                .subscribeOn(schedulersConfig.defaultScheduler())
                .filter(cozinhas -> !cozinhas.isEmpty())
                .switchIfEmpty(Maybe.just(List.of()))
                .blockingGet();
    }

    public Single<Kitchen> findById(Long id) {
        return Single.fromMaybe(Maybe.fromOptional(kitchenRepository.findById(id))
                .switchIfEmpty(Maybe.error(new NotFoundException(Utils.format("Kitchen of id {} not found.", id)))));
    }

    @Transactional
    public Single<Kitchen> save(Kitchen kitchen) {
        return Maybe.fromOptional(Optional.of(kitchenRepository.save(kitchen)))
                .toSingle()
                .onErrorResumeNext(e -> Single.error(new Exception(Utils.format("Erro while saving Kitchen."))));
    }

    @Transactional
    public Single<Kitchen> save(Kitchen kitchen, Long id) {
        return findById(id)
                .map(k -> {
                    BeanUtils.copyProperties(kitchen, k);
                    return kitchenRepository.save(k);
                })
                .onErrorResumeNext(e -> Single.error(new Exception(Utils.format("Error while saving kitchen with id {}", id))));
    }

    @Transactional
    public void saveAll(List<Kitchen> kitchens) {
        Observable.fromIterable(kitchens)
                .subscribeOn(schedulersConfig.defaultScheduler())
                .blockingSubscribe(kitchenRepository::save);
    }

    @Transactional
    public void deleteKitchen(Long id) {
        var managedKitchen = findById(id).blockingGet();
        kitchenRepository.delete(managedKitchen);
    }

}
