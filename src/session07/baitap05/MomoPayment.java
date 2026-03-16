package session07.baitap05;

public class MomoPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán MoMo: " + amount);
    }
}
