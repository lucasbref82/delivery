package br.com.delivery.v1.infrastructure.repositoryimpl;

import br.com.delivery.v1.domain.entity.State;
import org.springframework.stereotype.Repository;

@Repository
public class StateRepositoryImpl extends BaseRepositoryImpl<State, Long> {
    public StateRepositoryImpl() {
        super(State.class);
    }
}
