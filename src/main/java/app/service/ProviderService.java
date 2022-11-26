package app.service;

import app.persistence.entity.provider.Provider;

import java.util.List;

public interface ProviderService extends BaseCrudService<Provider> {

    List<Provider> findAll();
}
