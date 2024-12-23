package br.com.delivery.v1.infrastructure.repositoryimpl;

import br.com.delivery.v1.domain.entity.City;
import org.springframework.stereotype.Repository;

@Repository
public class CityRepositoryImpl extends BaseRepositoryImpl<City, Long> {
    public CityRepositoryImpl() {
        super(City.class);
    }
}
