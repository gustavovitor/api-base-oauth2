package com.gustavovitor.apibase.service.maker;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ServiceInterface<T> {
    Page<T> findAllPageable(Pageable pageable);

    T insert(T object);

    T update(Long objectId, T object);

    void delete(Long objectId);

    List<T> findAll();

    T findById(Long objectId);
}
