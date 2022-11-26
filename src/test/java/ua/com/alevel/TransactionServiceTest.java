package ua.com.alevel;


import app.UnitFinalProjectApplication;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import app.persistence.entity.transactions.Transaction;
import app.service.TransactionService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = UnitFinalProjectApplication.class)
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    private final int ITEM_SIZE = 10;

    @BeforeAll
    void shouldBeCreateTransaction() {
        for (int i = 0; i < ITEM_SIZE; ++i) {
            Transaction transaction = GenerationUtil.generateTransaction(i*100, "test"+i+"@mail.com");
            transactionService.create(transaction);
        }

        Assertions.assertEquals(ITEM_SIZE, transactionService.findAll().size());
    }

    @Order(1)
    @Test
    void shouldBeExceptionOnUpdateWithInvalidId() {
        Transaction transaction = transactionService.findAll().stream().findAny().get();
        Long id = transaction.getId();
        String email = transaction.getEmail();
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            transaction.setEmail("Test@mail.com");
            transaction.setId(100L);
            transactionService.update(transaction);
        });
        Assertions.assertEquals(email, transactionService.findById(id).get().getEmail());
    }
}
