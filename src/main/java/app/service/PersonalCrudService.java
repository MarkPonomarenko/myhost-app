package app.service;

import app.persistence.entity.user.Personal;

import java.util.List;

public interface PersonalCrudService extends BaseCrudService<Personal> {
    List<Personal> findAll();
}
