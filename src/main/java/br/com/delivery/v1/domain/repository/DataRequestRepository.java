package br.com.delivery.v1.domain.repository;

import br.com.delivery.v1.domain.entity.DataRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRequestRepository extends JpaRepository<DataRequest, Long> {
}
