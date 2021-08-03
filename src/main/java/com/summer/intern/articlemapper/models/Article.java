package com.summer.intern.articlemapper.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "articles", schema = "article_schema")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {
    @Id
    private String id;
    private String url;
    private Date timestamp;
    private String channel;
}
