package pizzashop.service;

import pizzashop.model.MenuDataModel;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;

import java.util.List;

public class PaymentService {

    private MenuRepository menuRepo;
    private PaymentRepository payRepo;
    private PaymentValidator paymentValidator;

    public PaymentService(MenuRepository menuRepo, PaymentRepository payRepo, PaymentValidator paymentValidator) {
        this.menuRepo=menuRepo;
        this.payRepo=payRepo;
        this.paymentValidator=paymentValidator;
    }

    public List<MenuDataModel> getMenuData(){return menuRepo.getMenu();}

    public List<Payment> getPayments(){return payRepo.getAll(); }

    public void addPayment(int table, PaymentType type, double amount){
        paymentValidator.validate(table, type, amount);
        Payment payment= new Payment(table, type, amount);
        payRepo.add(payment);
    }

    public double getTotalAmount(PaymentType type){
        double total=0.0f;
        List<Payment> l=getPayments();
        if ((l==null) ||(l.isEmpty())) return total;
        for (Payment p:l){
            if (p.getType().equals(type))
                total+=p.getAmount();
        }
        return total;
    }

}