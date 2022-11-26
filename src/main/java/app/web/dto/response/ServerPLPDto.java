package app.web.dto.response;

import app.persistence.entity.server.Server;
import app.persistence.type.CPU;

import java.util.Objects;

public class ServerPLPDto {

    private Long id;
    private String serverName;
    private Integer ram;
    private Boolean visible;
    private CPU cpuSeries;
    private String cpuModel;
    private Integer price;
    private Provider provider;

    public ServerPLPDto(Server server) {
        this.ram = server.getRam();
        this.serverName = server.getServerName();
        this.id = server.getId();
        this.visible = server.getVisible();
        this.cpuSeries = server.getCpuSeries();
        this.cpuModel = server.getCpuModel();
        this.price = server.getPrice();
        if (server.getProvider() != null) {
            this.provider = new Provider(
                    server.getProvider().getId(),
                    server.getProvider().getName());
        }
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public CPU getCpuSeries() {
        return cpuSeries;
    }

    public void setCpuSeries(CPU cpuSeries) {
        this.cpuSeries = cpuSeries;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "ServerPLPDto{" +
                "id=" + id +
                ", serverName='" + serverName + '\'' +
                ", visible=" + visible +
                ", cpuSeries=" + cpuSeries +
                ", cpuModel='" + cpuModel + '\'' +
                ", price=" + price +
                ", provider=" + provider +
                '}';
    }

    private static final class Provider {

        private final Long id;

        private final String name;

        public Provider(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (Provider) obj;
            return Objects.equals(this.id, that.id) &&
                    Objects.equals(this.name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }

        @Override
        public String toString() {
            return "Provider{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
