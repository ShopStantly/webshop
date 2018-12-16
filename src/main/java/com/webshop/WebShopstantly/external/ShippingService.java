package com.webshop.WebShopstantly.external;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ShippingService {

    public String saveOrder() throws IOException {
        OkHttpClient client = new OkHttpClient();

        String url = "http://localhost:8083/order/saveorder";

        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().toString();
    }

}
