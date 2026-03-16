package session07.baitap05;

public class HolidayDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount * 0.15;
    }
}
