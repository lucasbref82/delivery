package br.com.delivery.v1.service;

import br.com.delivery.configs.SchedulersConfig;
import br.com.delivery.v1.domain.entity.Restaurant;
import br.com.delivery.v1.infrastructure.repositoryimpl.RestaurantRepositoryImpl;
import io.reactivex.rxjava3.core.Maybe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
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
}
