package ua.com.alevel;

import app.UnitFinalProjectApplication;
import com.neovisionaries.i18n.CountryCode;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import app.persistence.entity.provider.Provider;
import app.service.ProviderService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = UnitFinalProjectApplication.class)
class ProviderServiceTest {

    @Autowired
    private ProviderService providerService;

    private final int ITEM_SIZE = 10;

    @BeforeAll
    void init() {
        for (int i = 0; i < ITEM_SIZE; i++) {
            Provider provider = GenerationUtil.generateProvider("first name" + i, CountryCode.YE);
            providerService.create(provider);
        }

        Assertions.assertEquals(ITEM_SIZE, providerService.findAll().size());
    }

    @Order(1)
    @Test
    void shouldBeCreateWithEmptyName() {
        Provider provider = GenerationUtil.generateProvider("first name 11", CountryCode.AX);
        providerService.create(provider);

        Assertions.assertEquals(ITEM_SIZE + 1, providerService.findAll().size());
    }

    @Order(2)
    @Test
    void shouldBeDeleteProvider() {
        Long id = providerService.findAll().stream().findFirst().get().getId();
        providerService.delete(id);
        Assertions.assertEquals(ITEM_SIZE, providerService.findAll().size());
    }

    @Order(3)
    @Test
    void shouldBeUpdateProvider() {
        Provider provider = providerService.findAll().stream().findFirst().get();
        provider.setName("MyHost");
        providerService.update(provider);
        Assertions.assertEquals("MyHost", providerService.findById(provider.getId()).get().getName());
    }

    @Order(4)
    @Test
    void shouldExceptionWhenUpdateWithInvalidId() {
        Provider provider = providerService.findAll().stream().findAny().get();
        Long id = provider.getId();
        String name = provider.getName();
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            provider.setName("Test");
            provider.setId(100L);
            providerService.update(provider);
        });
        Assertions.assertEquals(name, providerService.findById(id).get().getName());
    }

    @Order(5)
    @Test
    void shouldBeDeleteAllPersonals() {
        providerService.findAll().forEach(provider -> providerService.delete(provider.getId()));

        Assertions.assertEquals(0, providerService.findAll().size());
    }
}
