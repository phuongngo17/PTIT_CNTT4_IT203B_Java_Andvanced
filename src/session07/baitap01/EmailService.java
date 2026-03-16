package session07.baitap01;

public class EmailService {
    public void sendOrderConfirmation(Order order) {
        System.out.println("Đã gửi email " +
                order.getCustomer().getCustomerEmail()
                + ": Đơn hàng "
                + order.getOrderId()
                + " đã được tạo");
    }
}
