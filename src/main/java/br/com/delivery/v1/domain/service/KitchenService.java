package br.com.delivery.v1.domain.service;

import br.com.delivery.configs.SchedulersConfig;
import br.com.delivery.utils.Utils;
import br.com.delivery.v1.domain.entity.Kitchen;
import br.com.delivery.v1.domain.exception.NotFoundException;
import br.com.delivery.v1.domain.repository.KitchenRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KitchenService {

    private final KitchenRepository kitchenRepository;
    private final SchedulersConfig schedulersConfig;

    public Single<List<Kitchen>> findAll() {
        return Single.fromCallable(kitchenRepository::findAll)
                .subscribeOn(schedulersConfig.defaultScheduler())
                .filter(cozinhas -> !cozinhas.isEmpty())
                .switchIfEmpty(Single.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error listing kitchens.")));
    }

    public Single<Kitchen> findById(Long id) {
        return Single.fromMaybe(Maybe.fromOptional(kitchenRepository.findById(id))
                .switchIfEmpty(Maybe.error(new NotFoundException(Utils.format("Kitchen of id {} not found.", id)))));
    }

    public Single<Kitchen> save(Kitchen kitchen) {
        return Single.fromCallable(() -> kitchenRepository.save(kitchen))
                .onErrorResumeNext(e -> Single.error(new Exception(Utils.format("Erro while saving Kitchen."))));
    }

    public Single<Kitchen> update(Kitchen kitchen, Long id) {
        return findById(id)
                .map(k -> {
                    BeanUtils.copyProperties(kitchen, k);
                    return kitchenRepository.save(k);
                })
                .onErrorResumeNext(e -> Single.error(new Exception(Utils.format("Error while saving kitchen with id {}", id))));
    }

    public void saveAll(List<Kitchen> kitchens) {
        Observable.fromIterable(kitchens).subscribeOn(schedulersConfig.defaultScheduler()).blockingSubscribe(kitchenRepository::save);
    }

    public void deleteKitchen(Long id) {
        var managedKitchen = findById(id).blockingGet();
        kitchenRepository.delete(managedKitchen);
    }

}
