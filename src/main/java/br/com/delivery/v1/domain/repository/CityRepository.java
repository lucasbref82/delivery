package br.com.delivery.v1.domain.repository;

import br.com.delivery.v1.domain.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
