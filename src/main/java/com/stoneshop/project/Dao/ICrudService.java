package com.stoneshop.project.Dao;

import java.util.List;
import java.util.Optional;

public interface ICrudService<T> {
    Optional<T> find(Long id);
    void save(T model);
    void delete(T model);
    List<T> getAll();


}
