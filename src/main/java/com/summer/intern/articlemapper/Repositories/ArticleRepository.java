package com.summer.intern.articlemapper.Repositories;

import com.summer.intern.articlemapper.Models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ArticleRepository extends JpaRepository<Article,String> {

}
