package com.summer.intern.articlemapper.controllers;

import com.summer.intern.articlemapper.models.Article;
import com.summer.intern.articlemapper.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;
    @GetMapping("/getArticles")
    public ResponseEntity<List<Article>> getArticles(){
        return new ResponseEntity<>(articleService.getArticles(), HttpStatus.OK);
    }
    @GetMapping("/getArticle/{id}")
    public ResponseEntity<?> getArticle(@PathVariable String id){
        if(articleService.exist(id)){
            return new ResponseEntity<>(articleService.getArticle(id), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Article does not exist", HttpStatus.NOT_FOUND);
    }
    @PostMapping("/addArticle")
    public ResponseEntity<?> addArticle(@RequestBody Article article){
        if(!articleService.exist(article.getId())){
            articleService.addArticle(article);
            return new ResponseEntity<>("Article "+ article+" was created", HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("Article exists, use /updateArticle endpoint", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @PutMapping("/updateArticle")
    public ResponseEntity<?> updateArticle(@RequestBody Article article){
        if(articleService.exist(article.getId())){
            articleService.update(article);
            return new ResponseEntity<>("Article "+ article+" was updated", HttpStatus.ACCEPTED);
        }
        else
            return new ResponseEntity<>("Article does not exist, use /addArticle endpoint", HttpStatus.METHOD_NOT_ALLOWED);
    }
    @DeleteMapping("/deleteArticles")
    public ResponseEntity<?> deleteArticles(){
        articleService.deleteAll();
        return new ResponseEntity<>("Articles deleted", HttpStatus.OK);
    }
    @DeleteMapping("/deleteArticle/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable String id){
        if(articleService.exist(id)){
            articleService.delete(id);
            return new ResponseEntity<>("Article with id "+ id+" was deleted", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Article does not exist", HttpStatus.METHOD_NOT_ALLOWED);
    }
}
