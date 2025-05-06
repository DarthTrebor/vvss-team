package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTestStep2 {

    PaymentRepository payRepo;
    PaymentService service;
    PaymentValidator val;

    @BeforeEach
    void setUp() {
        payRepo = new PaymentRepository();
        val = new PaymentValidator();
        service = new PaymentService(null, payRepo, val);
    }

    @AfterEach
    void tearDown() {
        payRepo.getAll().clear();
        payRepo.writeAll();
    }

    @Test
    void getPayments() {
        List<Payment> result = service.getPayments();
        assertEquals(0, result.size());
    }

    @Test
    void addPayment() {
        // mock Payment
        Payment payment = mock(Payment.class);
        when(payment.getTableNumber()).thenReturn(3);
        when(payment.getType()).thenReturn(PaymentType.CASH);
        when(payment.getAmount()).thenReturn(3.0);

        // adaugare folosind valorile din mock
        service.addPayment(payment.getTableNumber(), payment.getType(), payment.getAmount());

        List<Payment> result = service.getPayments();
        assertEquals(1, result.size());

        Payment storedPayment = result.get(0);
        assertEquals(3, storedPayment.getTableNumber());
        assertEquals(PaymentType.CASH, storedPayment.getType());
        assertEquals(3.0, storedPayment.getAmount());
    }
}