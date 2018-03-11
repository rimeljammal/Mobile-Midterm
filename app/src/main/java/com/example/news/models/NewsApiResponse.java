package com.example.news.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by Rim on 07/03/2018.
 */

public interface NewsApiResponse {

    @GET("top-headlines")
    Call<List<ArticleItem>> getArticles(@Query("country") String country, @Query("category") String category);

}
