package org.example.service.impl;

import org.example.service.OrderService;
import org.springframework.stereotype.Component;

@Component // muốn khởi tạo phải có bean
public class OrderServicelmpl implements OrderService {
    @Override
    public void service() {
        System.out.println("Đang phục vụ khách hàng...");
    }
}
