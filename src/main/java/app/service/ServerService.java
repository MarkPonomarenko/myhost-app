package app.service;

import app.persistence.entity.server.Server;

import java.util.List;

public interface ServerService extends BaseCrudService<Server> {

    List<Server> findAll();
}
