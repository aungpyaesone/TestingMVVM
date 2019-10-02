package com.aa.a.reviewtalent.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
 private static Retrofit retrofit = null;

 static Retrofit getRetrofit(){

     HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
     interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
     OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

     Retrofit retrofit = new Retrofit.Builder()
             .baseUrl("https://newsapi.org/v2/")
             .client(okHttpClient)
             .addConverterFactory(GsonConverterFactory.create())
             .build();


     return retrofit;
 }
}
