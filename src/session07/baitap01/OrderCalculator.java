package session07.baitap01;

import java.util.Map;

public class OrderCalculator {
    public double calculateTotal(Order order) {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            total += product.getProductPrice()*quantity;
        }
        return total;
    }
}
