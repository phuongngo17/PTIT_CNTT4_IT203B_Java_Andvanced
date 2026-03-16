package session07.baitap01;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        System.out.println("Tạo sản phẩm");

        Product p1 = new Product("SP01", "Laptop", 15000000);
        Product p2 = new Product("SP02", "Chuột", 300000);

        System.out.println("Đã thêm sản phẩm SP01, SP02");

        System.out.println("Tạo khách hàng");

        Customer customer =
                new Customer("Nguyễn Văn A", "a@example.com", "Hà Nội");

        System.out.println("Đã thêm khách hàng");

        Map<Product, Integer> items = new HashMap<>();
        items.put(p1, 1);
        items.put(p2, 2);

        Order order = new Order("ORD001", customer, items);

        System.out.println("Đơn hàng ORD001 được tạo");

        OrderCalculator calculator = new OrderCalculator();

        System.out.println("Tính tổng tiền");

        double total = calculator.calculateTotal(order);
        order.setTotal(total);

        System.out.println("Tổng tiền: " + total);

        OrderRepository repository = new OrderRepository();

        System.out.println("Lưu đơn hàng");

        repository.save(order);

        EmailService emailService = new EmailService();

        System.out.println("Gửi email xác nhận");

        emailService.sendOrderConfirmation(order);
    }
}
