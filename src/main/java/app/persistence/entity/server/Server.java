package app.persistence.entity.server;


import app.persistence.entity.BaseEntity;
import app.persistence.entity.provider.Provider;
import app.persistence.entity.user.Personal;
import app.persistence.listener.ServerVisibleGenerationListener;
import app.persistence.type.CPU;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "servers")
@EntityListeners({
        ServerVisibleGenerationListener.class
})
public class Server extends BaseEntity {

    @Column(name = "server_name")
    private String serverName;

    @Column(name = "RAM")
    private Integer ram;

    @Column(nullable = false)
    private Integer price = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "cpu_series", nullable = true)
    private CPU cpuSeries;

    @Column(name = "cpu_model")
    private String cpuModel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Personal personal;

    public Date getNextPayment() {
        Calendar c = Calendar.getInstance();
        c.setTime(this.getUpdated());
        c.add(Calendar.MONTH, 1);
        return c.getTime();
    }

    public Server() {
        super();
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

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }
}
