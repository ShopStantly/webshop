package com.webshop.WebShopstantly.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Integer getArticlePrice(String article) {
        List<Article> articles = articleRepository.findAll();
        Optional<Article> wantedArticle = articles.stream().filter(a -> article.equals(a.getName())).findFirst();
        return wantedArticle.get().getPrice();
    }

    public Article getUser() {
        List<Article> users = articleRepository.findAll();
        return users.isEmpty() ? new Article() : users.get(0);
    }

    public void saveArticle() {
        Article user = new Article();
        user.setPrice(2);
        user.setName("milk");
        articleRepository.save(user);
    }

    public Article getArticle(String productToOrder) {
        List<Article> articles = articleRepository.findAll();
        Optional<Article> wantedArticle = articles.stream().filter(a -> productToOrder.equals(a.getName())).findFirst();
        return wantedArticle.get();
    }
}
