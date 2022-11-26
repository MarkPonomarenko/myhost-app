package app.persistence.repository.server;

import org.springframework.stereotype.Repository;
import app.persistence.entity.provider.Provider;
import app.persistence.repository.BaseRepository;

@Repository
public interface ProviderRepository extends BaseRepository<Provider> { }
