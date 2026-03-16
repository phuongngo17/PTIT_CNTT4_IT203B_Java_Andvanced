package session07.baitap05;

public class CODPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán COD: " + amount);
    }
}