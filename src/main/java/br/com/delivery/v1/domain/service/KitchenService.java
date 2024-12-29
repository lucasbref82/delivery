package br.com.delivery.v1.domain.service;

import br.com.delivery.configs.SchedulersConfig;
import br.com.delivery.utils.Utils;
import br.com.delivery.v1.domain.dto.GenericMessage;
import br.com.delivery.v1.domain.entity.Kitchen;
import br.com.delivery.v1.domain.exception.NotFoundException;
import br.com.delivery.v1.domain.repository.KitchenRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
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
        return Single.fromCallable(() -> kitchenRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Utils.format("Kitchen with id {} not found", id)))
        );
    }

    public Single<Kitchen> save(Kitchen kitchen) {
        return Single.fromCallable(() -> kitchenRepository.save(kitchen))
                .onErrorResumeNext(e -> Single.error(new Exception(Utils.format("Erro while saving Kitchen."))));
    }

    public Single<Kitchen> update(Kitchen kitchen, Long id) {
        return Maybe.fromOptional(kitchenRepository.findById(id))
                .flatMap(currentKitchen -> {
                    BeanUtils.copyProperties(kitchen, currentKitchen, "id");
                    return Maybe.just(kitchenRepository.save(currentKitchen));
                })
                .toSingle()
                .onErrorResumeNext(e -> Single.error(new Exception(Utils.format("Error to be try saved kitchen of id {}, cause {}", id, e.getMessage()))));
    }

    public void saveAll(List<Kitchen> kitchens) {
        Observable.fromIterable(kitchens).subscribeOn(schedulersConfig.defaultScheduler()).blockingSubscribe(kitchenRepository::save);
    }

    public Single<GenericMessage> deleteKitchen(Long id) {
        return this.findById(id)
                .flatMap(currentKitchen -> {
                    kitchenRepository.deleteById(currentKitchen.getId());
                    return Single.just(GenericMessage.builder()
                            .message(Utils.format("Kitchen of id {} successfully deleted", id))
                            .build());
                })
                .onErrorResumeNext(e -> {
                    if (e.getCause() instanceof ConstraintViolationException) {
                        return Single.error(new DataIntegrityViolationException(Utils.format("Error when trying to delete id {} kitchen because it is in use.", id)));
                    }
                    return Single.error(new Exception(Utils.format("Error when trying to delete id {} kitchen", id)));
                });
    }

}
