package pizzashop.service;

import org.junit.jupiter.api.Test;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTestGetTotalAmount {

    private PaymentService service = new PaymentService(null, null, null);

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