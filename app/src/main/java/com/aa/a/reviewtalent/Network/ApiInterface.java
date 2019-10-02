package com.aa.a.reviewtalent.Network;

import com.aa.a.reviewtalent.model.NewRespone;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<NewRespone> getNewsList(@Query("sources") String newsSource,
                                 @Query("apiKey") String apiKey);

}
