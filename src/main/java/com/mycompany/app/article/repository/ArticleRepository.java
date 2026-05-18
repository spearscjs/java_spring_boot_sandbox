package com.mycompany.app.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycompany.app.article.entity.JsonPlaceholderPostEntity;

public interface ArticleRepository extends JpaRepository<JsonPlaceholderPostEntity, Integer> {
}