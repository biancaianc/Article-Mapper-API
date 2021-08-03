package com.summer.intern.articlemapper;

import com.summer.intern.articlemapper.models.Article;
import com.summer.intern.articlemapper.repositories.ArticleRepository;
import com.summer.intern.articlemapper.services.ArticleService;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;


import java.util.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;


public class ArticleServiceTest {
    ArticleRepository articleRepository = Mockito.mock(ArticleRepository.class);
    ArticleService articleService = new ArticleService(articleRepository);

    @Test
    public void getArticles_happyPath() {
        List<Article> expectedResult = Arrays.asList(new Article("aaa", "dsdd", new Date(), "bbb"), new Article("111", "dsdd", new Date(), "bbb"));
        List<Article> providedArray = Arrays.asList(new Article("aaa", "dsdd", new Date(), "bbb"), new Article("111", "dsdd", new Date(), "bbb"));

        Mockito.when(articleRepository.findAll()).thenReturn(providedArray);
        List<Article> result = articleService.getArticles();
        Mockito.verify(articleRepository, Mockito.times(1)).findAll();
        assertEquals(expectedResult, result);
    }

    @Test
    public void exist_happyPath() {
        boolean expectedResult = true;
        Mockito.when(articleRepository.existsById(any(String.class))).thenReturn(true);
        boolean result = articleService.exist("aaa");
        Mockito.verify(articleRepository, Mockito.times(1)).existsById(any(String.class));
        assertEquals(expectedResult, result);


    }

    @Test
    public void exist_sadPath() {
        boolean expectedResult = false;
        Mockito.when(articleRepository.existsById(any(String.class))).thenReturn(false);
        boolean result = articleService.exist("aaa");
        Mockito.verify(articleRepository, Mockito.times(1)).existsById(any(String.class));
        assertEquals(expectedResult, result);


    }

    @Test
    public void getArticle_happyPath() {
        Article expectedResult = new Article("aaa", "dsdd", new Date(), "bbb");
        Article providedArticle = new Article("aaa", "dsdd", new Date(), "bbb");

        Mockito.when(articleRepository.findById(providedArticle.getId())).thenReturn(Optional.of(providedArticle));
        Article result = articleService.getArticle("aaa");
        Mockito.verify(articleRepository, Mockito.times(1)).findById(providedArticle.getId());
        assertEquals(expectedResult, result);
    }

    @Test
    public void addArticle_happyPath() {

        Mockito.when(articleRepository.save(any(Article.class))).thenReturn(new Article());
        articleService.addArticle(new Article());
        Mockito.verify(articleRepository, Mockito.times(1)).save(any(Article.class));

    }

    @Test
    public void updateArticle_happyPath() {

        Article expectedArticle = new Article("aaa", "dsdd", new Date(), "bbb");
        Article providedArticle = new Article("aaa", "dsdd", new Date(), "bbb");
        Mockito.when(articleRepository.save(providedArticle)).thenReturn(expectedArticle);
        articleService.update(providedArticle);
        Mockito.verify(articleRepository, Mockito.times(1)).save(providedArticle);

    }

    @Test
    public void deleteAll_happyPath() {
        articleService.deleteAll();
        Mockito.verify(articleRepository, Mockito.times(1)).deleteAll();
    }

    @Test
    public void delete_happyPath() {
        Article providedArticle = new Article("aaa", "dsdd", new Date(), "bbb");
        articleService.delete(providedArticle.getId());
        Mockito.verify(articleRepository, Mockito.times(1)).deleteById(providedArticle.getId());
    }


}
