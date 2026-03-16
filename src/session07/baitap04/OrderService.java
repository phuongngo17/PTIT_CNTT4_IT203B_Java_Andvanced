package session07.baitap04;

public class OrderService {

    private OrderRepository orderRepository;
    private NotificationService notificationService;

    public OrderService(OrderRepository orderRepository,
                        NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
    }

    public void createOrder(String orderId) {

        Order order = new Order(orderId);

        System.out.println("Tạo đơn hàng " + orderId);

        orderRepository.save(order);

        notificationService.send(
                "Đơn hàng " + orderId + " đã được tạo",
                "customer"
        );
    }
}