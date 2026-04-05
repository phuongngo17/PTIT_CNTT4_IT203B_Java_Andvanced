package org.example;

import org.example.configs.AppConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        //bean - đọc cấu hình java class bean
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        // lấy bean ra như thế nào ?
        Person fullstack = (Person) context.getBean("fullstack"); // gọi tên hàm
        System.out.println(fullstack);
    }
}