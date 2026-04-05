package org.example;

import org.example.configs.AppConfiguration;
import org.example.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTestDI {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        CustomerService customerService = context.getBean("customerService", CustomerService.class);
         customerService.eat();
         customerService.sleep();
    }
}
