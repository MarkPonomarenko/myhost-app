package app.persistence.crud;

import app.persistence.datatable.DataTableRequest;
import app.persistence.datatable.DataTableResponse;
import app.persistence.entity.BaseEntity;
import app.persistence.repository.BaseRepository;

import java.util.Optional;

public interface CrudRepositoryHelper <E extends BaseEntity, R extends BaseRepository<E>> {

    void create(R repository, E entity);

    void update(R repository, E entity);

    void delete(R repository, Long id);

    Optional<E> findById(R repository, Long id);

    DataTableResponse<E> findAll(R repository, DataTableRequest request);

}