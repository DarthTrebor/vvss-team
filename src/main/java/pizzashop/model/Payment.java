package pizzashop.model;

import java.util.Objects;

public class Payment {
    private int tableNumber;
    private PaymentType type;
    private double amount;
    public Payment(int tableNumber, PaymentType type, double amount) {
        this.tableNumber = tableNumber;
        this.type = type;
        this.amount = amount;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return tableNumber + ","+type +"," + amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return tableNumber == payment.tableNumber && Double.compare(payment.amount, amount) == 0 && type == payment.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableNumber, type, amount);
    }
}
