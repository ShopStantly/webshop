package com.webshop.WebShopstantly;

import com.webshop.WebShopstantly.external.InventoryService;
import com.webshop.WebShopstantly.external.PaymentService;
import com.webshop.WebShopstantly.internal.Article;
import com.webshop.WebShopstantly.internal.ArticleService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("")
public class WebshopController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<String> orderProduct(@RequestBody String productToOrder) throws IOException {
        Integer articlePrice = articleService.getArticlePrice(productToOrder);
        Article article = articleService.getArticle(productToOrder);
        Integer balance = paymentService.getBalanceForUser();

        if (articlePrice < balance) {
            inventoryService.successfullyOrderdArticle(article);
            return new ResponseEntity<>("du darfst es kaufen!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("You do not have enough balance!", HttpStatus.BAD_REQUEST);
        }

//        switch(productToOrder) {
//            case "milk":
//                Integer articlePrice = articleService.getArticlePrice(productToOrder);
//                String balance = getBalanceForUser();
//                return balance;
//            default:
//                return "Product does not exist in our shop.";
//        }
    }

}
