package session07.baitap05;


import java.util.List;

public class OrderService {

    private OrderRepository repository;
    private NotificationService notification;

    public OrderService(OrderRepository repository,
                        NotificationService notification) {
        this.repository = repository;
        this.notification = notification;
    }

    public Order createOrder(String id,
                             Customer customer,
                             @org.jetbrains.annotations.NotNull List<OrderItem> items,
                             DiscountStrategy discount,
                             PaymentMethod payment){

        double total = 0;

        for(OrderItem item : items){
            total += item.getTotalPrice();
        }

        double discountAmount = discount.applyDiscount(total);

        double finalAmount = total - discountAmount;

        payment.pay(finalAmount);

        Order order = new Order(id, customer, items, finalAmount);

        repository.save(order);

        notification.send("Đơn hàng đã tạo", customer.getEmail());

        return order;
    }
}