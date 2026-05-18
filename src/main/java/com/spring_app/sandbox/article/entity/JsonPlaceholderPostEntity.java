package com.spring_app.sandbox.article.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "articles")
public class JsonPlaceholderPostEntity {

    @Id
    private Integer id;

    private Integer userId;
    private String title;

    @Column(length = 5000)
    private String body;

    // REQUIRED by JPA
    public JsonPlaceholderPostEntity() {}

    // constructor we will use when mapping DTO → Entity
    public JsonPlaceholderPostEntity(Integer id, Integer userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    // getters & setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }
}