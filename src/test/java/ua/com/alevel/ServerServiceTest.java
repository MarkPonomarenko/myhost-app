package ua.com.alevel;

import app.UnitFinalProjectApplication;
import com.neovisionaries.i18n.CountryCode;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import app.facade.ServerFacade;
import app.persistence.entity.provider.Provider;
import app.persistence.entity.server.Server;
import app.persistence.type.CPU;
import app.service.ProviderService;
import app.service.ServerService;
import app.web.dto.request.ServerRequestDto;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = UnitFinalProjectApplication.class)
class ServerServiceTest {

    @Autowired
    private ServerService serverService;

    @Autowired
    private ServerFacade serverFacade;

    @Autowired
    private ProviderService providerService;

    private final int ITEM_SIZE = 10;

    @BeforeAll
    void init() {
        Provider provider = new Provider();
        provider.setName("test");
        provider.setCountry(CountryCode.CO);
        providerService.create(provider);
        Long providerId = providerService.findAll().stream().findFirst().get().getId();
        for (int i = 0; i < ITEM_SIZE; i++) {
            ServerRequestDto server = GenerationUtil.generateServerRequest("name" + i, i, i, CPU.AMD, "model"+i, providerId);
            System.out.println(i);
            serverFacade.create(server);
            System.out.println(serverService.findAll().size());
        }
        System.out.println(providerService.findAll().stream().findFirst().get().getServers().size());
        Assertions.assertEquals(ITEM_SIZE, serverService.findAll().size());
        Assertions.assertEquals(ITEM_SIZE, providerService.findAll().stream().findFirst().get().getServers().size());
    }

    @Order(1)
    @Test
    void shouldBeCreateWithEmptyName() {
        Provider provider = providerService.findAll().stream().findFirst().get();
        Server server = GenerationUtil.generateServer("",11, 11, CPU.Intel, "model test", provider);
        serverService.create(server);

        Assertions.assertEquals(ITEM_SIZE + 1, serverService.findAll().size());
    }

    @Order(2)
    @Test
    void shouldBeUpdateServer() {
        Server server = serverService.findAll().stream().findFirst().get();
        server.setServerName("update");
        serverService.update(server);
        Long id = server.getId();
        Assertions.assertEquals("update", serverService.findById(id).get().getServerName());
    }

    @Order(3)
    @Test
    void shouldBeDeleteServer() {
        Long id = serverService.findAll().stream().findFirst().get().getId();
        serverService.delete(id);
        Assertions.assertEquals(ITEM_SIZE, serverService.findAll().size());
    }

    @Order(4)
    @Test
    void shouldExceptionWhenUpdateWithInvalidId() {
        Server server = serverService.findAll().stream().findAny().get();
        Long id = server.getId();
        String name = server.getServerName();
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            server.setServerName("Test");
            server.setId(100L);
            serverService.update(server);
        });
        Assertions.assertEquals(name, serverService.findById(id).get().getServerName());
    }

    @Order(5)
    @Test
    void shouldBeDeleteAllServers() {
        serverService.findAll().forEach(server -> serverService.delete(server.getId()));
        providerService.findAll().forEach(provider -> providerService.delete(provider.getId()));

        Assertions.assertEquals(0, serverService.findAll().size());
        Assertions.assertEquals(0, providerService.findAll().size());
    }
}
