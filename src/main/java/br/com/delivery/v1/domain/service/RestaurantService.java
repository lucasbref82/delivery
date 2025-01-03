package br.com.delivery.v1.domain.service;

import br.com.delivery.configs.SchedulersConfig;
import br.com.delivery.utils.Utils;
import br.com.delivery.v1.domain.entity.Restaurant;
import br.com.delivery.v1.domain.exception.BusinessException;
import br.com.delivery.v1.domain.exception.NotFoundException;
import br.com.delivery.v1.domain.exception.ServiceException;
import br.com.delivery.v1.domain.repository.RestaurantRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantService {

    // Repository
    private final RestaurantRepository restaurantRepository;

    // Service
    private final KitchenService kitchenService;

    // Configs
    private final SchedulersConfig schedulersConfig;

    public Maybe<List<Restaurant>> findAll() {
        return Maybe.fromCallable(restaurantRepository::findAll)
                .subscribeOn(schedulersConfig.defaultScheduler())
                .filter(r -> !r.isEmpty())
                .switchIfEmpty(Maybe.just(List.of()));
    }

    public Single<Restaurant> findById(Long id) {
        return Maybe.fromOptional(restaurantRepository.findById(id))
                .switchIfEmpty(Maybe.error(new NotFoundException(Utils.format("Restaurant with id {} not found"))))
                .toSingle();
    }

    public Single<Restaurant> save(Restaurant restaurant) {
        if (restaurant.getKitchen() != null) {
            return kitchenService.findById(restaurant.getKitchen().getId())
                    .flatMap(kitchen -> {
                        restaurant.setKitchen(kitchen);
                        return Single.just(restaurantRepository.save(restaurant));
                    })
                    .onErrorResumeNext(e -> {
                        if (e.getCause() instanceof NotFoundException) {
                            return Single.error(new BusinessException(Utils.format("Error while saving cause: {}", e.getMessage())));
                        }
                        return Single.error(new ServiceException("Error while saving restaurant.", e));
                    });
        } else {
            return Single.fromCallable(() -> restaurantRepository.save(restaurant))
                    .onErrorResumeNext(e -> Single.error(new ServiceException("Error while saving restaurant.", e)));
        }
    }

    public void delete(Long id) {
        findById(id)
                .blockingSubscribe(restaurantRepository::delete);
    }
}
