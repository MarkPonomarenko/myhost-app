package app.web.dto.request;

import app.persistence.type.CPU;

public class ServerRequestDto extends RequestDto{

    private String serverName;
    private Integer ram;
    private Integer price;
    private Long providerId;
    private CPU cpuSeries;
    private String cpuModel;

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

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
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

    @Override
    public String toString() {
        return "ServerRequestDto{" +
                "serverName='" + serverName + '\'' +
                ", RAM=" + ram +
                ", price=" + price +
                ", providerId=" + providerId +
                ", cpuSeries=" + cpuSeries +
                ", cpuModel='" + cpuModel + '\'' +
                '}';
    }
}
