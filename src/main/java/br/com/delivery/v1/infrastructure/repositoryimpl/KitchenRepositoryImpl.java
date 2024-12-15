package br.com.delivery.v1.infrastructure.repositoryimpl;

import br.com.delivery.v1.domain.entity.Kitchen;
import org.springframework.stereotype.Repository;

@Repository
public class KitchenRepositoryImpl extends BaseRepositoryImpl<Kitchen, Long> {
    public KitchenRepositoryImpl() {
        super(Kitchen.class);
    }
}
