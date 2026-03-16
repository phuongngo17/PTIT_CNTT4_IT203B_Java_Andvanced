package session07.baitap04;

public class Main {

    public static void main(String[] args) {

        OrderRepository repository = new FileOrderRepository();
        NotificationService notification = new EmailService();

        OrderService orderService = new OrderService(repository, notification);

        orderService.createOrder("ORD001");

        System.out.println("------------");

        OrderRepository repository2 = new DatabaseOrderRepository();
        NotificationService notification2 = new SMSNotification();

        OrderService orderService2 = new OrderService(repository2, notification2);

        orderService2.createOrder("ORD002");
    }
}
