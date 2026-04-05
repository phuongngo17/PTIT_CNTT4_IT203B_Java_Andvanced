package org.example.Baitap01;

public class RechargeService {
    //private PaymentGateway gateway;

    public RechargeService() {
        // Lỗi: Tự khởi tạo thủ công (Hard-code dependency)
        //vi phạm nguyên lý IoC (Inversion of Control) và không sử dụng Dependency Injection (DI)
        //RechargeService phụ thuộc trực tiếp vào InternalPaymentGateway
        //Nếu muốn dùng thêm:
        //MomoPaymentGateway
        //ZaloPayPaymentGateway
        //Class tự tạo dependency thay vì nhận từ bên ngoài
        //this.gateway = new InternalPaymentGateway();
    }

    public void processRecharge(String username, double amount) {
        //gateway.pay(amount);
        System.out.println("Nạp " + amount + " cho " + username);
    }
}