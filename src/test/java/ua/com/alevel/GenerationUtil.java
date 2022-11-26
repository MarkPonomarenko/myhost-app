package ua.com.alevel;

import com.neovisionaries.i18n.CountryCode;
import app.persistence.entity.provider.Provider;
import app.persistence.entity.server.Server;
import app.persistence.entity.transactions.Transaction;
import app.persistence.entity.user.Personal;
import app.persistence.type.CPU;
import app.persistence.type.Roles;
import app.web.dto.request.ServerRequestDto;

public class GenerationUtil {

    private GenerationUtil() {

    }

    public static Personal generatePersonal(String firstName, String lastName, String email, String password) {

        Personal personal = new Personal();

        personal.setFirstName(firstName);
        personal.setLastName(lastName);
        personal.setEmail(email);
        personal.setPassword(password);
        personal.setRole(Roles.ROLE_PERSONAL);
        return personal;
    }

    public static Provider generateProvider(String name, CountryCode code) {
        Provider provider = new Provider();

        provider.setName(name);
        provider.setCountry(code);
        return provider;
    }

    public static Server generateServer(String name, Integer ram, Integer price,
                                        CPU cpu, String model, Provider provider) {
        Server server = new Server();
        server.setServerName(name);
        server.setRam(ram);
        server.setPrice(price);
        server.setCpuSeries(cpu);
        server.setCpuModel(model);
        server.setProvider(provider);
        return server;
    }

    public static ServerRequestDto generateServerRequest(String name, Integer ram, Integer price,
                                                         CPU cpu, String model, Long providerId) {
        ServerRequestDto serverRequestDto = new ServerRequestDto();
        serverRequestDto.setServerName(name);
        serverRequestDto.setRam(ram);
        serverRequestDto.setPrice(price);
        serverRequestDto.setCpuSeries(cpu);
        serverRequestDto.setCpuModel(model);
        serverRequestDto.setProviderId(providerId);
        return serverRequestDto;
    }

    public static Transaction generateTransaction(Integer amount, String email) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setEmail(email);
        return transaction;
    }
}
