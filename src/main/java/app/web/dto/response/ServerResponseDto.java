package app.web.dto.response;

import app.persistence.entity.provider.Provider;
import app.persistence.entity.server.Server;
import app.persistence.entity.user.Personal;
import app.persistence.type.CPU;

public class ServerResponseDto extends ResponseDto{

    private String serverName;
    private Integer ram;
    private Integer price;
    private Provider provider;
    private Personal personal;
    private CPU cpuSeries;
    private String cpuModel;

    public ServerResponseDto() { }

    public ServerResponseDto(Server server) {
        setId(server.getId());
        setCreated(server.getCreated());
        setUpdated(server.getUpdated());
        setVisible(server.getVisible());
        this.serverName = server.getServerName();
        this.ram = server.getRam();
        this.price =server.getPrice();
        this.provider = server.getProvider();
        this.cpuSeries = server.getCpuSeries();
        this.cpuModel = server.getCpuModel();
        if (server.getPersonal() != null) {
            this.personal = server.getPersonal();
        }
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
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

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
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

}
