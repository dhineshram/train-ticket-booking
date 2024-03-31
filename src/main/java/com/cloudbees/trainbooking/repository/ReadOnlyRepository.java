package com.cloudbees.trainbooking.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ReadOnlyRepository<T, K> extends Repository<T, K> {

    Optional<T> findById(K id);
    List<T> findAll();
    List<T> findAllById(Iterable<K> ids);
    boolean existsById(K id);

}
