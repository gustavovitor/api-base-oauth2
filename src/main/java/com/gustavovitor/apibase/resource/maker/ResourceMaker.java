package com.gustavovitor.apibase.resource.maker;

import com.gustavovitor.apibase.service.maker.ServiceMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings({"unchecked", "SpringJavaInjectionPointsAutowiringInspection"})
public class ResourceMaker<S extends ServiceMaker, T> implements ResourceInterface<S, T> {

    @Autowired
    private S service;

    @Override
    @GetMapping
    public ResponseEntity<List<T>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<Page<T>> findAllPageable(Pageable pageable) {
        return ResponseEntity.ok(service.findAllPageable(pageable));
    }

    @Override
    @PostMapping
    public ResponseEntity<T> insert(@RequestBody @Valid T object) {
        return (ResponseEntity<T>) ResponseEntity.ok(service.insert(object));
    }

    @Override
    @PutMapping("/{objectId}")
    public ResponseEntity<T> update(@PathVariable Long objectId, @RequestBody @Valid T object) {
        return (ResponseEntity<T>) ResponseEntity.ok(service.update(objectId, object));
    }

    @Override
    @DeleteMapping("/{objectId}")
    public void delete(@PathVariable Long objectId) {
        service.delete(objectId);
    }
}
