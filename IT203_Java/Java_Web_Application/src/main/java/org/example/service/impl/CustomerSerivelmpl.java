package org.example.service.impl;

import org.example.service.CustomerService;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // k có nghĩa nhưng nó là bean để khởi tạo
public class CustomerSerivelmpl implements CustomerService {
    //DI _tiệm  sự phụ thuộc
    @Autowired // sử dụng annotation @autowired để DI field
    private OrderService orderService;
    // Bao nhiêu cách tiệm DI-3
    //contructor
//    public CustomerSerivelmpl(OrderService orderService){
//        this.orderService = orderService;
//    }
    // setter
//    public void setOrderService(OrderService orderService) {
//        this.orderService = orderService;
//    }
    //field


    @Override
    public void eat() {
        orderService.service();
    }

    @Override
    public void sleep() {
        System.out.println("Đang ngủ để thư giản...");
    }
}
