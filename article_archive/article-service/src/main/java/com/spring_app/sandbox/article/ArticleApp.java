package com.spring_app.sandbox.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.spring_app.sandbox")
public class ArticleApp {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApp.class, args);
    }
}
