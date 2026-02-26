package com.example;

import com.example.services.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        var service = ctx.getBean(UserService.class);
        System.out.println(service);
        ctx.close();
    }
}
