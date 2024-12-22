package br.com.delivery.v1.domain.service;

import br.com.delivery.configs.SchedulersConfig;
import br.com.delivery.utils.Utils;
import br.com.delivery.v1.domain.entity.State;
import br.com.delivery.v1.domain.exception.NotFoundException;
import br.com.delivery.v1.infrastructure.repositoryimpl.StateRepository;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateService {

    private final StateRepository stateRepository;
    private final SchedulersConfig schedulersConfig;

    public List<State> findAll() {
        return Maybe.fromCallable(stateRepository::findAll)
                .subscribeOn(schedulersConfig.defaultScheduler())
                .filter(state -> !state.isEmpty())
                .switchIfEmpty(Maybe.just(List.of()))
                .blockingGet();
    }

    public Maybe<State> findById(Long id) {
        return Maybe.fromOptional(stateRepository.findById(id))
                .switchIfEmpty(Maybe.error(new NotFoundException(Utils.format("State of id {} not found.", id))));
    }

    public State save(State state) {
        return Maybe.fromOptional(stateRepository.save(state)).blockingGet();
    }

    public Single<State> update(Long id, State state) {
        return findById(id)
                .switchIfEmpty(Maybe.error(new NotFoundException(Utils.format("State of id {} not found", id))))
                .flatMap(s -> {
                    BeanUtils.copyProperties(state, s);
                    return stateRepository.update(s)
                            .map(Maybe::just)
                            .orElse(Maybe.error(new Exception("Failed to update state")));
                })
                .toSingle();
    }
}
