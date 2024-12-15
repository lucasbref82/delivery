package br.com.delivery.v1.infrastructure.repositoryimpl;

import br.com.delivery.v1.domain.repository.BaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Id;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public class BaseRepositoryImpl<T, ID extends Serializable> implements BaseRepository<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public BaseRepositoryImpl() {
        // Obtém o tipo genérico da classe ao invés de passar no construtor
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public List<T> findAll() {
        String query = "SELECT e FROM " + entityClass.getName() + " e";
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
        try {
            for (var field : entity.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    field.setAccessible(true);
                    return (ID) field.get(entity);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
