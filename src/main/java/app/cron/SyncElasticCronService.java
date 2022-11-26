package app.cron;


import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import app.elastic.index.ServerIndex;
import app.elastic.repository.ServerIndexRepository;
import app.persistence.entity.server.Server;
import app.persistence.repository.server.ServerRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class SyncElasticCronService {

    private final ElasticsearchOperations elasticsearchOperations;
    private final ServerRepository serverRepository;
    private final ServerIndexRepository serverIndexRepository;

    public SyncElasticCronService(ElasticsearchOperations elasticsearchOperations, ServerRepository serverRepository, ServerIndexRepository serverIndexRepository) {
        this.elasticsearchOperations = elasticsearchOperations;
        this.serverRepository = serverRepository;
        this.serverIndexRepository = serverIndexRepository;
    }

    @Scheduled(fixedDelay = 60000)
    public void syncToSupplier() {
        elasticsearchOperations.indexOps(ServerIndex.class).refresh();
        serverIndexRepository.deleteAll();
        serverIndexRepository.saveAll(prepareDataset());
    }

    private Collection<ServerIndex> prepareDataset() {
        List<Server> servers = serverRepository.findAll();
        List<ServerIndex> serverIndices = new ArrayList<>();
        servers.forEach(server -> {
            ServerIndex serverIndex = new ServerIndex();
            serverIndex.setModel(server.getCpuModel());
            serverIndices.add(serverIndex);
        });
        return serverIndices;
    }
}
