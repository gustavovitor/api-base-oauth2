package com.gustavovitor.apibase.resource.maker;

import com.gustavovitor.apibase.service.maker.ServiceMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@SuppressWarnings({"unchecked", "SpringJavaInjectionPointsAutowiringInspection"})
public class ResourceMaker<S extends ServiceMaker, T> implements ResourceInterface<S, T> {

    @Autowired
    private S service;

    @Override
    @PreAuthorize("hasAuthority(#root.this.roleRead) and #oauth2.hasScope('read')")
    public ResponseEntity<T> findById(Long objectId) {
        return (ResponseEntity<T>) ResponseEntity.ok(service.findById(objectId));
    }

    @Override
    @GetMapping
    @PreAuthorize("hasAuthority(#root.this.roleRead) and #oauth2.hasScope('write')")
    public ResponseEntity<List<T>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    @GetMapping("/page")
    @PreAuthorize("hasAuthority(#root.this.roleRead) and #oauth2.hasScope('write')")
    public ResponseEntity<Page<T>> findAllPageable(Pageable pageable) {
        return ResponseEntity.ok(service.findAllPageable(pageable));
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority(#root.this.roleWrite) and #oauth2.hasScope('write')")
    public ResponseEntity<T> insert(@RequestBody @Valid T object) {
        return (ResponseEntity<T>) ResponseEntity.ok(service.insert(object));
    }

    @Override
    @PutMapping("/{objectId}")
    @PreAuthorize("hasAuthority(#root.this.roleWrite) and #oauth2.hasScope('write')")
    public ResponseEntity<T> update(@PathVariable Long objectId, @RequestBody @Valid T object) {
        return (ResponseEntity<T>) ResponseEntity.ok(service.update(objectId, object));
    }

    @Override
    @DeleteMapping("/{objectId}")
    @PreAuthorize("hasAuthority(#root.this.roleDelete) and #oauth2.hasScope('write')")
    public void delete(@PathVariable Long objectId) {
        service.delete(objectId);
    }

    protected String getRoleRead() { return ""; }
    protected String getRoleWrite() { return ""; }
    protected String getRoleDelete() { return ""; }
}
