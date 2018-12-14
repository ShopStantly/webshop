package com.webshop.WebShopstantly.external;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webshop.WebShopstantly.internal.Article;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class InventoryService {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public void successfullyOrderdArticle(Article article) throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = "http://localhost:8082/inventory/successful-order";

        ObjectMapper mapper = new ObjectMapper();
        String articleAsString = mapper.writeValueAsString(article); // Convert java object to json

//        Article article1 = mapper.readValue(articleAsString, Article.class); // String (json) to Article

        RequestBody body = RequestBody.create(JSON, articleAsString);

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
    }

//    public boolean successfullyOrderdArticles(Article article, int number) throws IOException {
//        ArticleAndNumber articleAndNumber = new ArticleAndNumber();
//        articleAndNumber.article = article;
//        articleAndNumber.amount = number;
//
//        OkHttpClient client = new OkHttpClient();
//
//        String url = "http://localhost:8081/inventory/successful-order";
//
//        ObjectMapper mapper = new ObjectMapper();
//        String articleAndNumberAsString = mapper.writeValueAsString(articleAndNumber); // Convert java object to json
//
////        Article article1 = mapper.readValue(articleAsString, Article.class);
//
//        RequestBody body = RequestBody.create(JSON, articleAndNumberAsString);
//
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//
//        Response response = client.newCall(request).execute();
//        return Boolean.parseBoolean(response.body().string());
//    }
//
//    class ArticleAndNumber {
//        public Article article;
//        public int amount;
//    }

}
