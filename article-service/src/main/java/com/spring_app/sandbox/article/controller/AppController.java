package com.spring_app.sandbox.article.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring_app.sandbox.article.entity.JsonPlaceholderPostEntity;
import com.spring_app.sandbox.article.service.ApiService;

@RestController
public class AppController {

    private final ApiService apiService;

    @GetMapping("/articles/hello")
    public String hello() {
        return "Hello World from Spring Boot!";
    }

    @GetMapping("/articles/{resourceId}")
    public String hello(@PathVariable String resourceId) {
        return "Hello World from Spring Boot! Resource ID: " + resourceId;
    }

    @GetMapping("/articles/posts")
    public List<Map<String, Object>> getPosts() {
        return apiService.getPosts();
    }

    public AppController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/articles/load")
    public String loadArticles() {
        apiService.loadPosts();
        return "Articles loaded into DB!";
    }

    @GetMapping("/articles")
    public List<JsonPlaceholderPostEntity> getArticles() {
        return apiService.getAllArticlesFromDb();
    }
}
