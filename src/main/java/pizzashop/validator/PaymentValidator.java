package pizzashop.validator;

import pizzashop.model.PaymentType;

public class PaymentValidator {
    public PaymentValidator() {
        //empty constructor
    }

    public void validate(int table, PaymentType type, double amount) {

        if (table < 1 || table > 8) {
            throw new IllegalArgumentException("Table number must be between 1 and 8.");
        }

        if (type != PaymentType.CASH && type != PaymentType.CARD) {
            throw new IllegalArgumentException("Payment type must be either CASH or CARD.");
        }

        if (amount <= 0.0 || amount > 1000.0) {
            throw new IllegalArgumentException("Amount must be between 0 and 1000.");
        }
    }
}
