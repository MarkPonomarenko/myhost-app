package app.service;

import app.persistence.datatable.DataTableRequest;
import app.persistence.datatable.DataTableResponse;
import app.persistence.entity.BaseEntity;

import java.util.Optional;

public interface BaseCrudService<E extends BaseEntity> {

    void create(E entity);

    void update(E entity);

    void delete(Long id);

    Optional<E> findById(Long id);

    DataTableResponse<E> findAll(DataTableRequest request);
}
