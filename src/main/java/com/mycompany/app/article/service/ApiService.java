package com.mycompany.app.article.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mycompany.app.article.dto.JsonPlaceholderPostDTO;
import com.mycompany.app.article.entity.JsonPlaceholderPostEntity;
import com.mycompany.app.article.repository.ArticleRepository;

@Service
public class ApiService {

    
    private final ArticleRepository articleRepository;
    private final RestTemplate restTemplate = new RestTemplate();
  
    public ApiService(ArticleRepository repository) {
        this.articleRepository = repository;
    }


    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> getPosts() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        // Returns JSON array mapped to List of Maps
        return restTemplate.getForObject(url, List.class);
    }

    public List<JsonPlaceholderPostEntity> loadPosts() {
        String url = "https://jsonplaceholder.typicode.com/posts";
        JsonPlaceholderPostDTO[] postsArray = restTemplate.getForObject(url, JsonPlaceholderPostDTO[].class);
        
    
        List<JsonPlaceholderPostEntity> entities = Arrays.stream(postsArray)
        .map(dto -> new JsonPlaceholderPostEntity(
                dto.getId(),
                dto.getUserId(),
                dto.getTitle(),
                dto.getBody()
        ))
        .toList();

        return articleRepository.saveAll(entities);

    }

    public List<JsonPlaceholderPostEntity> getAllArticlesFromDb() {
        return articleRepository.findAll();
    }

    

}
