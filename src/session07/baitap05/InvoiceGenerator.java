package session07.baitap05;

public class InvoiceGenerator {
    public void generate(Order order){
        System.out.println("=== HÓA ĐƠN ===");
        System.out.println("Khách: " + order.getCustomer().getName());
        System.out.println("Cần thanh toán: " + order.getFinalAmount());
    }

}