package com.gustavovitor.apibase.resource.maker;

import com.gustavovitor.apibase.service.maker.ServiceMaker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResourceInterface<S extends ServiceMaker, T> {
    public ResponseEntity<T> findById(Long objectId);

    public ResponseEntity<List<T>> findAll();

    public ResponseEntity<Page<T>> findAllPageable(Pageable pageable);

    public ResponseEntity<T> insert(T object);

    public ResponseEntity<T> update(Long objectId, T object);

    public void delete(Long objectId);
}
