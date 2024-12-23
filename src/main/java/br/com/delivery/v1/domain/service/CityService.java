package br.com.delivery.v1.domain.service;

import br.com.delivery.configs.SchedulersConfig;
import br.com.delivery.utils.Utils;
import br.com.delivery.v1.domain.entity.City;
import br.com.delivery.v1.domain.exception.NotFoundException;
import br.com.delivery.v1.domain.exception.ServiceException;
import br.com.delivery.v1.infrastructure.repositoryimpl.CityRepositoryImpl;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepositoryImpl cityRepository;

    private final SchedulersConfig schedulersConfig;

    private final StateService stateService;

    public Maybe<List<City>> findAll() {
        return Maybe.fromCallable(cityRepository::findAll)
                .subscribeOn(schedulersConfig.defaultScheduler())
                .filter(cities -> !cities.isEmpty())
                .switchIfEmpty(Maybe.just(List.of()));
    }

    public Single<City> findById(Long id) {
        return Maybe.fromOptional(cityRepository.findById(id))
                .switchIfEmpty(Maybe.error(new NotFoundException(Utils.format("City of id {} does not exist", id))))
                .toSingle();
    }

    public Single<City> save(City city) {
        if (city.getState() != null) {
            return stateService.findById(city.getState().getId())
                    .flatMap(state -> {
                        city.setState(state);
                        return Maybe.fromOptional(cityRepository.save(city));
                    })
                    .toSingle()
                    .onErrorResumeNext(e -> Single.error(new ServiceException("Error while saving city", e)));
        }
        return Maybe.fromOptional(cityRepository.save(city))
                .toSingle()
                .onErrorResumeNext(e -> Single.error(new ServiceException("Error while saving city", e)));
    }

    public void delete(Long id) {
        findById(id)
                .blockingSubscribe(c -> cityRepository.delete(id));
    }
}
