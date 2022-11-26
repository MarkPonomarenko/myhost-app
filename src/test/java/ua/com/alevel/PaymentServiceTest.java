package ua.com.alevel;

import app.UnitFinalProjectApplication;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import app.persistence.entity.user.Personal;
import app.service.PaymentService;
import app.service.PersonalCrudService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = UnitFinalProjectApplication.class)
public class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PersonalCrudService personalCrudService;

    private final int ITEM_SIZE = 10;

    @Order(1)
    @Test
    void shouldBeMoneyTaken() {

        Personal personal = new Personal();
        personal.setFirstName("test");
        personal.setLastName("test");
        personal.setBalance(1000);
        personal.setEmail("test@mail.com");
        personal.setPassword("password");
        personalCrudService.create(personal);
        Personal personal1 = personalCrudService.findAll().stream().findFirst().get();
        paymentService.paymentProcess(personal1.getId(), 300);
        Assertions.assertEquals(700, personalCrudService.findById(personal1.getId()).get().getBalance());
    }
}
