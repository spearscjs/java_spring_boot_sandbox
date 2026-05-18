package com.spring_app.sandbox.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring_app.sandbox.article.entity.JsonPlaceholderPostEntity;

public interface ArticleRepository extends JpaRepository<JsonPlaceholderPostEntity, Integer> {
}