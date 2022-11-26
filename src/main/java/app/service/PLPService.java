package app.service;

import app.persistence.entity.server.Server;

import java.util.List;
import java.util.Map;

public interface PLPService {

    List<Server> search(Map<String, Object> queryMap);
}
