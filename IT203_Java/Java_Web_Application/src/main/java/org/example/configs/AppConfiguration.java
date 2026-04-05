package org.example.configs;

import org.example.Person;
import org.example.service.CustomerService;
import org.example.service.OrderService;
import org.example.service.impl.CustomerSerivelmpl;
import org.example.service.impl.OrderServicelmpl;
import org.springframework.context.annotation.Bean;

public class AppConfiguration {
    @Bean
    //bean có tên nhưng nếu không ghi mặc định lấy tên hàm
    public Person fullstack(){
        Person hoangThaiMinh = new Person();
        hoangThaiMinh.setId(1);
        hoangThaiMinh.setName("Phuong");
        hoangThaiMinh.setMajor("Full Stack");
        return hoangThaiMinh;
    }
    @Bean
    public OrderService orderService(){
        return new OrderServicelmpl();
    }
    @Bean
    public CustomerService customerService(){
        return new CustomerSerivelmpl();
    }
}
