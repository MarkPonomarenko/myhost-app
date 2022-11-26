package app.persistence.repository.server;

import org.springframework.stereotype.Repository;
import app.persistence.entity.provider.Provider;
import app.persistence.entity.server.Server;
import app.persistence.repository.BaseRepository;

import java.util.List;

@Repository
public interface ServerRepository extends BaseRepository<Server> {

    List<Server> findByProvider(Provider provider);
    List<Server> findByCpuModelContaining(String cpuModel);
}
