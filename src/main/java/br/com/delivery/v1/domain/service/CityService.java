package br.com.delivery.v1.domain.service;

import br.com.delivery.utils.Utils;
import br.com.delivery.v1.domain.dto.GenericMessage;
import br.com.delivery.v1.domain.entity.City;
import br.com.delivery.v1.domain.exception.NotFoundException;
import br.com.delivery.v1.domain.exception.ServiceException;
import br.com.delivery.v1.domain.repository.CityRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    private final StateService stateService;

    public Single<List<City>> findAll() {
        return Single.fromCallable(cityRepository::findAll)
                .filter(cities -> !cities.isEmpty())
                .switchIfEmpty(Single.just(List.of()));
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
                        return Single.just(cityRepository.save(city));
                    })
                    .onErrorResumeNext(e -> Single.error(new ServiceException("Error while saving city", e)));
        }
        return Single.fromCallable(() -> cityRepository.save(city))
                .onErrorResumeNext(e -> Single.error(new ServiceException("Error while saving city", e)));
    }

    public Single<GenericMessage> delete(Long id) {
        return findById(id)
                .flatMap(x -> {
                    cityRepository.delete(x);
                    return Single.just(GenericMessage
                            .builder()
                            .success(true)
                            .message(Utils.format("City of id {} deleted with successfully.", id))
                            .build());
                }).onErrorResumeNext(e -> Single.error(new Exception(Utils.format("Error when trying to delete city id {}", id))));
    }
}
