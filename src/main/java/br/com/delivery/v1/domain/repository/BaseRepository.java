package br.com.delivery.v1.domain.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T, ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    Optional<T> update(T t);
    Optional<T> save(T t);
    void delete(ID id);

}
