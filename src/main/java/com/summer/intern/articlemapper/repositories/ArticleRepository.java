package com.summer.intern.articlemapper.repositories;

import com.summer.intern.articlemapper.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,String> {

}
