package org.example.Baitap05;

import org.example.Baitap05.model.SystemConfig;
import org.example.bth.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {

        // Khởi tạo IoC Container bằng Java Config
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        // Lấy Bean từ container
        SystemConfig config = context.getBean(SystemConfig.class);

        // In kết quả
        System.out.println("Chi nhánh: " + config.getBranchName());
        System.out.println("Giờ mở cửa: " + config.getOpeningHour());
    }
}