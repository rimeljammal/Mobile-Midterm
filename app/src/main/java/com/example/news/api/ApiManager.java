package com.example.news.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Rim on 07/03/2018.
 */

public class ApiManager {

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private NewsApiResponse newsApiResponse;

    private final String base_url = "https://newsapi.org/v2/top-headlines?";

    public ApiManager() {
        Gson gson = new GsonBuilder().create();
        okHttpClient = new OkHttpClient.Builder().addNetworkInterceptor(new NewsInterceptor()).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        newsApiResponse = retrofit.create(NewsApiResponse.class);
    }

    private static class NewsInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl originalUrl = request.url();
            HttpUrl modifiedUrl = originalUrl
                    .newBuilder()
                    .addQueryParameter("APPID", "c9f44d63b1934d42bb97ba2891c0adab")
                    .build();
            Request modifiedRequest = request.newBuilder().url(modifiedUrl).build();
            return chain.proceed(modifiedRequest);
        }
    }
}