package app.persistence.entity.provider;


import com.neovisionaries.i18n.CountryCode;
import app.persistence.entity.BaseEntity;
import app.persistence.entity.server.Server;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "providers")
public class Provider extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private CountryCode country;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "provider")
    private Set<Server> servers;

    public Provider() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryCode getCountry() {
        return country;
    }

    public void setCountry(CountryCode country) {
        this.country = country;
    }

    public Set<Server> getServers() {
        return servers;
    }

    public void setServers(Set<Server> servers) {
        this.servers = servers;
    }

    public void addServer(Server server) {
        this.servers.add(server);
    }

    public void deleteServer(Server server) {
        this.servers.remove(server);
    }
}
