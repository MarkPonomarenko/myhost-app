package app.persistence.listener;

import app.persistence.entity.server.Server;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class ServerVisibleGenerationListener {

    @PostLoad
    @PostPersist
    @PostUpdate
    public void generateServerVisible(Server server) {
        server.setVisible(server.getPersonal() == null);
    }
}
