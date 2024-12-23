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

    public  Maybe<State> save(State state) {
        return Maybe.fromOptional(Optional.of(stateRepository.save(state)));
    }

    public Single<State> update(Long id, State state) {
        return findById(id)
                .flatMap(s -> {
                    BeanUtils.copyProperties(state, s);
                    return Optional.of(stateRepository.save(s))
                            .map(Maybe::just)
                            .orElse(Maybe.error(new Exception("Failed to update state")));
                })
                .toSingle();
    }

    public void delete(Long id) {
        findById(id)
                .blockingSubscribe(stateRepository::delete);
    }
}
