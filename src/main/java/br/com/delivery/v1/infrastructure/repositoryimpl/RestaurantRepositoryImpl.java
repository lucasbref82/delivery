package br.com.delivery.v1.infrastructure.repositoryimpl;

import br.com.delivery.v1.domain.entity.Restaurant;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepositoryImpl extends BaseRepositoryImpl<Restaurant, Long>{
    public RestaurantRepositoryImpl() {
        super(Restaurant.class);
    }
}
