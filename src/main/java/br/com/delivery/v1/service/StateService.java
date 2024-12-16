package br.com.delivery.v1.service;

import br.com.delivery.configs.SchedulersConfig;
import br.com.delivery.utils.Utils;
import br.com.delivery.v1.domain.entity.State;
import br.com.delivery.v1.domain.exception.NotFoundException;
import br.com.delivery.v1.infrastructure.repositoryimpl.StateRepository;
import io.reactivex.rxjava3.core.Maybe;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
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

    public State findById(Long id) {
        return Maybe.fromOptional(stateRepository.findById(id)).switchIfEmpty(Maybe.error(new NotFoundException(Utils.format("State of id {} not found.", id)))).blockingGet();
    }

}
