package com.spring_app.sandbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        System.out.println("Starting Spring Boot app...");
        SpringApplication.run(App.class, args);
    }
}