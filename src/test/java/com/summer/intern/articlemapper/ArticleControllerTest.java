package com.summer.intern.articlemapper;



import com.summer.intern.articlemapper.models.Article;
import com.summer.intern.articlemapper.repositories.ArticleRepository;
import com.summer.intern.articlemapper.services.ArticleService;
import org.junit.jupiter.api.Test;


import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import utils.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(classes = ArticleMapperApplication.class)
public class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleService articleService;

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    @Test
    public void getArticles_happy() throws Exception {
        Article providedArticle1 = new Article("aaa", "dsdd", parseDate("2014-02-14"), "bbb");
        Article providedArticle2 = new Article("bbb", "dsdd", parseDate("2014-02-14"), "bbb");
        when(articleRepository.findAll()).thenReturn(Arrays.asList(providedArticle1, providedArticle2));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getArticles"))
                .andExpect(status().isOk())
                .andExpect(content().string(StringUtils.readFileAsString("src/test/resources/get-articles-response.json")));
        Mockito.verify(articleRepository, Mockito.times(1)).findAll();
    }


    @Test
    public void getArticle_happy() throws Exception {
        Article providedArticle = new Article("aaa", "dsdd", parseDate("2014-02-14"), "bbb");
        when(articleRepository.existsById(providedArticle.getId())).thenReturn(true);
        when(articleRepository.findById(providedArticle.getId())).thenReturn(Optional.of(providedArticle));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getArticle/{id}", providedArticle.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(StringUtils.readFileAsString("src/test/resources/get-article-response.json")));

        Mockito.verify(articleRepository, Mockito.times(1)).existsById(providedArticle.getId());
        Mockito.verify(articleRepository, Mockito.times(1)).findById(providedArticle.getId());
    }

    @Test
    public void getArticle_sad() throws Exception {
        Article providedArticle = new Article("aaa", "dsdd", new Date(), "bbb");
        when(articleRepository.existsById(providedArticle.getId())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/getArticle/{id}", providedArticle.getId()))
                .andExpect(status().isNotFound());
        Mockito.verify(articleRepository, Mockito.times(1)).existsById(providedArticle.getId());
        Mockito.verify(articleRepository, Mockito.times(0)).findById(providedArticle.getId());
    }

    @Test
    public void addArticle_happy() throws Exception {
        Article providedArticle = new Article("aaa", "dsdd", new Date(), "bbb");
        when(articleRepository.existsById(providedArticle.getId())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/addArticle")
                .content(StringUtils.asJsonString(providedArticle))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
        Mockito.verify(articleRepository, Mockito.times(1)).existsById(providedArticle.getId());
        Mockito.verify(articleRepository, Mockito.times(1)).save(providedArticle);

    }


    @Test
    public void addArticle_sad() throws Exception {
        Article providedArticle = new Article("aaa", "dsdd", new Date(), "bbb");
        when(articleRepository.existsById(providedArticle.getId())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/addArticle")
                .content(StringUtils.asJsonString(providedArticle))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
        Mockito.verify(articleRepository, Mockito.times(1)).existsById(providedArticle.getId());
        Mockito.verify(articleRepository, Mockito.times(0)).save(providedArticle);
    }

    @Test
    public void updateArticle_happy() throws Exception {
        Article providedArticle = new Article("aaa", "dsdd", new Date(), "bbb");
        when(articleRepository.existsById(providedArticle.getId())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/updateArticle")
                .content(StringUtils.asJsonString(providedArticle))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
        Mockito.verify(articleRepository, Mockito.times(1)).existsById(providedArticle.getId());
        Mockito.verify(articleRepository, Mockito.times(1)).save(providedArticle);

    }

    @Test
    public void updateArticle_sad() throws Exception {
        Article providedArticle = new Article("aaa", "dsdd", new Date(), "bbb");
        when(articleRepository.existsById(providedArticle.getId())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/updateArticle")
                .content(StringUtils.asJsonString(providedArticle))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
        Mockito.verify(articleRepository, Mockito.times(1)).existsById(providedArticle.getId());
        Mockito.verify(articleRepository, Mockito.times(0)).save(providedArticle);

    }

    @Test
    public void deleteArticles() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/deleteArticles"))
                .andExpect(status().isOk());
        Mockito.verify(articleRepository, Mockito.times(1)).deleteAll();

    }

    @Test
    public void deleteArticle_happy() throws Exception {
        Article providedArticle = new Article("aaa", "dsdd", new Date(), "bbb");
        when(articleRepository.existsById(providedArticle.getId())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/deleteArticle/{id}", providedArticle.getId()))
                .andExpect(status().isOk());
        Mockito.verify(articleRepository, Mockito.times(1)).existsById(providedArticle.getId());
        Mockito.verify(articleRepository, Mockito.times(1)).deleteById(providedArticle.getId());

    }

    @Test
    public void deleteArticle_sad() throws Exception {
        Article providedArticle = new Article("aaa", "dsdd", new Date(), "bbb");
        when(articleRepository.existsById(providedArticle.getId())).thenReturn(false);
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/deleteArticle/{id}", providedArticle.getId()))
                .andExpect(status().isMethodNotAllowed());
        Mockito.verify(articleRepository, Mockito.times(1)).existsById(providedArticle.getId());
        Mockito.verify(articleRepository, Mockito.times(0)).deleteById(providedArticle.getId());
    }

}
