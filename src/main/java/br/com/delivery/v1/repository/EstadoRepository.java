package br.com.delivery.v1.repository;

import br.com.delivery.v1.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
