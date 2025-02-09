package br.com.delivery.v1.repository;

import br.com.delivery.v1.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Integer> {
}
