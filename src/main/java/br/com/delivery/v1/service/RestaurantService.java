package br.com.delivery.v1.service;

import br.com.delivery.configs.SchedulersConfig;
import br.com.delivery.utils.Utils;
import br.com.delivery.v1.domain.entity.Restaurant;
import br.com.delivery.v1.domain.exception.NotFoundException;
import br.com.delivery.v1.infrastructure.repositoryimpl.RestaurantRepositoryImpl;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantService {

    private final RestaurantRepositoryImpl restaurantRepository;
    private final SchedulersConfig schedulersConfig;

    public List<Restaurant> findAll() {
        return Maybe.fromCallable(restaurantRepository::findAll)
                .subscribeOn(schedulersConfig.defaultScheduler())
                .filter(r -> !r.isEmpty())
                .switchIfEmpty(Maybe.just(List.of()))
                .blockingGet();
    }

    public Restaurant findById(Long id) {
        return Maybe
                .fromOptional(restaurantRepository.findById(id))
                .switchIfEmpty(Maybe.error(new NotFoundException(Utils.format("Restaurant with id {} not found"))))
                .blockingGet();
    }

    public List<Restaurant> saveAll(List<Restaurant> restaurants) {
        return Observable
                .fromIterable(restaurants)
                .subscribeOn(schedulersConfig.defaultScheduler())
                .map(r -> restaurantRepository.save(r).orElse(null))
                .onErrorResumeNext(e -> {
                    log.error("Erro ao salvar restaurante, motivo: {}", e.getMessage(), e);
                    return Observable.error(e);
                })
                .toList()
                .blockingGet();
    }

    public Restaurant save(Restaurant restaurant) {
        return Observable.fromOptional(restaurantRepository.save(restaurant)).blockingFirst();
    }

    public void delete(Long id) {
        restaurantRepository.delete(id);
    }
}
