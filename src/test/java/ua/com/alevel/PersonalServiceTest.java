package ua.com.alevel;

import app.UnitFinalProjectApplication;
import app.exception.EntityNotFoundException;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import app.exception.EntityExistException;
import app.persistence.entity.user.Personal;
import app.service.PersonalCrudService;
import org.springframework.test.context.junit4.SpringRunner;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = UnitFinalProjectApplication.class)
class PersonalServiceTest {

    @Autowired
    private PersonalCrudService personalCrudService;

    private final int ITEM_SIZE = 10;

    @BeforeAll
    void init() {
        for (Personal personal : personalCrudService.findAll()) {
            personalCrudService.delete(personal.getId());
        }
        for (int i = 0; i < ITEM_SIZE; i++) {
            Personal personal = GenerationUtil.generatePersonal("first name" + i, "last name" + i, "test" + i + "@gmail.com", "password" + i);
            personalCrudService.create(personal);
            System.out.println(i);
        }

        Assertions.assertEquals(ITEM_SIZE, personalCrudService.findAll().size());
    }

    @Order(1)
    @Test
    void shouldBeExceptionWhenCreatingWithExistingEmail() {
        Exception exception = Assertions.assertThrows(EntityExistException.class, () -> {
            Personal personal = GenerationUtil.generatePersonal("first name", "last name", "test2@gmail.com", "password");
            personalCrudService.create(personal);
        });

        Assertions.assertEquals(ITEM_SIZE, personalCrudService.findAll().size());
    }

    @Order(2)
    @Test
    void shouldBeDeletePersonal() {
        Long id = personalCrudService.findAll().stream().findFirst().get().getId();
        personalCrudService.delete(id);
        Assertions.assertEquals(ITEM_SIZE - 1, personalCrudService.findAll().size());
    }

    @Order(3)
    @Test
    void shouldBeUpdatePersonal() {
        Personal personal = personalCrudService.findAll().stream().findFirst().get();
        personal.setFirstName("Mark");
        personalCrudService.update(personal);
        Assertions.assertEquals("Mark", personalCrudService.findById(personal.getId()).get().getFirstName());
    }

    @Order(4)
    @Test
    void shouldExceptionWhenUpdateWithInvalidId() {
        Personal personal = personalCrudService.findAll().stream().findAny().get();
        Exception exception = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            personal.setFirstName("Test");
            personal.setId(100L);
            personalCrudService.update(personal);
        });
        Assertions.assertEquals(ITEM_SIZE - 1, personalCrudService.findAll().size());
    }

    @Order(5)
    @Test
    void shouldBeDeleteAllPersonals() {
        personalCrudService.findAll().forEach(personal -> personalCrudService.delete(personal.getId()));

        Assertions.assertEquals(0, personalCrudService.findAll().size());
    }
}
