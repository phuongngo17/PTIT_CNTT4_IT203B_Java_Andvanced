package org.example.bth;

import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

public class AppConfig {
    //bean - Normalnotify
    @Bean
    public NormalNotify normalNotify(){
        return new NormalNotify();
    }
    // bean - vip
    @Bean
    public VIPNotify vipNotify(){
        return new VIPNotify();
    }
    // bean - accountManagement
    @Bean
    public AccountManagement accountManagement(){
        Map<String,Double> students = new HashMap<>();
        students.put("Đinh Quốc Khánh",1000.0);
        students.put("Doãn Ngọc Duy",6000.0);
        students.put("Nguyễn Tiến Minh",3.600);
        return new AccountManagement(students);
    }
}
