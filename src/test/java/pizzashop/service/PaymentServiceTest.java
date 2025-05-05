package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {
    private PaymentService service;

    @BeforeEach
    void setUp() {
        MenuRepository repoMenu = new MenuRepository();
        PaymentRepository repository = new PaymentRepository();
        PaymentValidator validator = new PaymentValidator();
        this.service = new PaymentService(repoMenu, repository, validator);

        repository.getAll().clear();
        repository.writeAll();
    }

    @Test
    @Order(1)
    @DisplayName("EC Test 1")
    void addValidPaymentTest() {
        service.addPayment(1, PaymentType.CARD, 200.5);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(2)
    @DisplayName("EC Test 2")
    void addValidPaymentTest2() {
        service.addPayment(4, PaymentType.CARD, 100.75);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(3)
    @DisplayName("EC Test 3")
    void addValidPaymentTest3() {
        service.addPayment(6, PaymentType.CARD, 45.7);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(4)
    @DisplayName("EC Test 4")
    void addValidPaymentTest4() {
        service.addPayment(8, PaymentType.CARD, 28.9);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(5)
    @DisplayName("EC Test 5")
    void addInvalidPayment() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(-10, PaymentType.CARD, 800.4);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Table number must be between 1 and 8"), "Exception message does not match expected text.");
    }


    @Test
    @Order(6)
    @DisplayName("EC Test 6")
    void addInvalidPayment2() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(121, PaymentType.CARD, 455);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Table number must be between 1 and 8"), "Exception message does not match expected text.");
    }

    @Test
    @Order(7)
    @DisplayName("EC Test 7")
    void addInvalidPayment3() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(4, PaymentType.CARD, 25000);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Amount must be between 0 and 1000."), "Exception message does not match expected text.");
    }

    @Test
    @Order(8)
    @DisplayName("EC Test 8")
    void addInvalidPayment4() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(7, PaymentType.CARD, -7.5);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Amount must be between 0 and 1000."), "Exception message does not match expected text.");

    }

    @Test
    @Order(9)
    @Tag("BVA")
    @DisplayName("BVA Test 1")
    void addValidPaymentTestBVA() {
        service.addPayment(3, PaymentType.CARD, 33.3);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(10)
    @DisplayName("BVA Test 2")
    void addInvalidPaymentBVA() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(9, PaymentType.CARD, 33.3);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Table number must be between 1 and 8"), "Exception message does not match expected text.");
    }

    @Test
    @Order(11)
    @DisplayName("BVA Test 3")
    void addInvalidPaymentBVA2() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(0, PaymentType.CARD, 33.3);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Table number must be between 1 and 8"), "Exception message does not match expected text.");
    }

    @Test
    @Order(12)
    @DisplayName("BVA Test 4")
    void addValidPaymentTestBVA2() {
        service.addPayment(5, PaymentType.CARD, 450.65);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(13)
    @DisplayName("BVA Test 5")
    void addInvalidPaymentBVA3() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(5, PaymentType.CARD, 0);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Amount must be between 0 and 1000."), "Exception message does not match expected text.");
    }

    @Test
    @Order(14)
    @DisplayName("BVA Test 6")
    void addInvalidPaymentBVA4() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            service.addPayment(5, PaymentType.CARD, 1000.1);
        }, "Expected addPayment to throw, but it didn't");

        assertTrue(thrown.getMessage().contains("Amount must be between 0 and 1000."), "Exception message does not match expected text.");
    }

    @Test
    void getTotalAmountTest1() {
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new Payment(5, PaymentType.CASH, 13.0f));
        double result = service.getTotalAmount(paymentList, PaymentType.CASH);
        assertEquals(13.0f, result);
    }

    @Test
    void getTotalAmountTest2() {
        List<Payment> paymentList = new ArrayList<>();
        double result = service.getTotalAmount(paymentList, PaymentType.CASH);
        assertEquals(0.0f, result);
    }

    @Test
    void getTotalAmountTest3() {
        double result = service.getTotalAmount(null, PaymentType.CASH);
        assertEquals(0.0f, result);
    }

    @Test
    void getTotalAmountTest4() {
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new Payment(1, PaymentType.CARD, 12.0f));
        paymentList.add(new Payment(2, PaymentType.CASH, 13.0f));
        paymentList.add(new Payment(3, PaymentType.CARD, 12.0f));
        double result = service.getTotalAmount(paymentList, PaymentType.CASH);
        assertEquals(13.0f, result);
    }

    @Test
    void getTotalAmountTest5() {
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new Payment(5, PaymentType.CASH, 13.0f));
        double result = service.getTotalAmount(paymentList, PaymentType.CARD);
        assertEquals(0.0f, result);
    }
}