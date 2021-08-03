package com.summer.intern.articlemapper.services;

import com.summer.intern.articlemapper.models.Article;
import com.summer.intern.articlemapper.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getArticles() {
//        List<Article> articles = articleRepository.findAll();
//        articles = articles.stream().map(article -> {
//            article.setId(article.getId().toUpperCase());
//            return article;
//        }).collect(Collectors.toList());
//        return articles;
        return articleRepository.findAll();
    }

    public boolean exist(String id) {
        return articleRepository.existsById(id);
    }

    public Article getArticle(String id) {
        return articleRepository.findById(id).get();
    }

    public void addArticle(Article article) {
        articleRepository.save(article);
    }

    public void update(Article article) {
        articleRepository.save(article);
    }

    public void deleteAll() {
        articleRepository.deleteAll();
    }

    public void delete(String id) {
        articleRepository.deleteById(id);
    }
}
