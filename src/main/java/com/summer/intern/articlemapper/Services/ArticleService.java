package com.summer.intern.articlemapper.Services;

import com.summer.intern.articlemapper.Models.Article;
import com.summer.intern.articlemapper.Repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;
    public List<Article> getArticles() {
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
