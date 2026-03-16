package session07.baitap05;

public class VNPayPayment implements PaymentMethod {
    @Override
    public void pay(double amount) {
        System.out.println("Thanh toán VNPay: " + amount);
    }
}
