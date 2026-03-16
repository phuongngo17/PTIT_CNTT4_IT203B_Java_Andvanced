package session07.baitap02;

public class FixedDiscount implements DiscountStrategy {
    private double discountAmount;
    public FixedDiscount(double discountAmount) {
        this.discountAmount = discountAmount;
    }
    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount - discountAmount;
    }
}
