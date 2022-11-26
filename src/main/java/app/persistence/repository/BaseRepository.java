package app.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import app.persistence.entity.BaseEntity;

@NoRepositoryBean
public interface BaseRepository <E extends BaseEntity> extends JpaRepository<E, Long>, JpaSpecificationExecutor<E> { }
