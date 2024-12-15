package br.com.delivery.v1.infrastructure.repositoryimpl;

import br.com.delivery.v1.domain.repository.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class BaseRepositoryImpl<T, ID> implements BaseRepository<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> entityClass;

    public BaseRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<T> findAll() {
        String query = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        Query typedQuery = entityManager.createQuery(query, entityClass);
        return typedQuery.getResultList();
    }

    @Override
    public Optional<T> findById(ID id) {
        T entity = entityManager.find(entityClass, id);
        return Optional.ofNullable(entity);
    }

    @Override
    @Transactional
    public Optional<T> update(T entity) {
        return save(entity);
    }

    @Override
    @Transactional
    public Optional<T> save(T entity) {
        return Optional.of(entityManager.merge(entity));
    }

    @Override
    @Transactional
    public void delete(ID id) {
        var managedObject = findById(id).orElseThrow();
        entityManager.remove(managedObject);
    }

    @SuppressWarnings("unchecked")
    private ID getEntityId(T entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        return (ID) field.get(entity);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Failed to access entity ID field", e);
                    }
                }).orElse(null);
    }
}
