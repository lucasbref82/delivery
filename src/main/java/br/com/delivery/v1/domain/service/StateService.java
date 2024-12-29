package br.com.delivery.v1.domain.service;

import br.com.delivery.configs.SchedulersConfig;
import br.com.delivery.utils.Utils;
import br.com.delivery.v1.domain.entity.State;
import br.com.delivery.v1.domain.exception.NotFoundException;
import br.com.delivery.v1.domain.repository.StateRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StateService {

    private final StateRepository stateRepository;
    private final SchedulersConfig schedulersConfig;

    public Maybe<List<State>> findAll() {
        return Maybe.fromCallable(stateRepository::findAll)
                .subscribeOn(schedulersConfig.defaultScheduler())
                .filter(state -> !state.isEmpty())
                .switchIfEmpty(Maybe.just(List.of()));
    }

    public Single<State> findById(Long id) {
        return Maybe.fromOptional(stateRepository.findById(id))
                .toSingle()
                .onErrorResumeNext(e -> Single.error(new NotFoundException(Utils.format("State of id {} not found."))));
    }

    public  Single<State> save(State state) {
        return Single.fromCallable(() -> stateRepository.save(state))
                .onErrorResumeNext(e -> Single.error(new Exception("Error when trying to save state.")));
    }

    public Single<State> update(Long id, State state) {
        return findById(id)
                .flatMap(s -> {
                    BeanUtils.copyProperties(state, s, "id");
                    return Single.just(stateRepository.save(s));
                })
                .onErrorResumeNext(e -> Single.error(new Exception("Error when trying to update state of id {}.")));
    }

    public void delete(Long id) {
        findById(id)
                .blockingSubscribe(stateRepository::delete);
    }
}
