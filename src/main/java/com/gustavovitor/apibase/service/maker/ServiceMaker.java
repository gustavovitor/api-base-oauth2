package com.gustavovitor.apibase.service.maker;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@SuppressWarnings({"unchecked", "SpringJavaInjectionPointsAutowiringInspection"})
public class ServiceMaker<R extends JpaRepository, T> implements ServiceInterface<T> {

    @Autowired
    protected R repository;

    @Override
    public Page<T> findAllPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public T insert(T object) {
        return (T) repository.save(object);
    }

    @Override
    public T update(Long objectId, T object) {
        T savedObject = findById(objectId);
        BeanUtils.copyProperties(object, savedObject);
        return savedObject;
    }

    @Override
    public void delete(Long objectId) {
        repository.delete(findById(objectId));
    }

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public T findById(Long objectId) {
        try {
            return (T) repository.findById(objectId)
                    .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada."));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
