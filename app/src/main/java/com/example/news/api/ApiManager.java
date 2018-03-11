package com.example.news.api;

import com.example.news.models.ArticleItem;
import com.example.news.models.NewsApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rim on 07/03/2018.
 */

public class ApiManager {

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private NewsApiResponse newsApiResponse;

    private final String base_url = "https://newsapi.org/v2/";

    public ApiManager() {
        Gson gson = new GsonBuilder().create();
        okHttpClient = new OkHttpClient
                .Builder()
                .addNetworkInterceptor(new NewsInterceptor())
                .build();
        retrofit = new Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        newsApiResponse = retrofit.create(NewsApiResponse.class);
    }

    public Call<List<ArticleItem>> getArticles(String country, String category) {
        return newsApiResponse.getArticles(country, category);
    }

    private static class NewsInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl originalUrl = request.url();
            HttpUrl modifiedUrl = originalUrl
                    .newBuilder()
                    .addQueryParameter("apiKey", "682f7044cfa84ea5a9da533e5060816a")
                    .build();
            Request modifiedRequest = request.newBuilder().url(modifiedUrl).build();
            return chain.proceed(modifiedRequest);
        }
    }

}