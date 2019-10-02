package com.aa.a.reviewtalent.Network;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.aa.a.reviewtalent.model.NewRespone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewRepository  {

    private static NewRepository newRepository;

    public static NewRepository getInstance(){

        if(newRepository == null)
        {
            newRepository = new NewRepository();
        }
        return newRepository;

    }



    private ApiInterface apiInterface;
    public NewRepository(){
        //APIInterface apiInterface = APIClient.getRetrofit().create(APIInterface.class);
        apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
    }

    public MutableLiveData<NewRespone> getNews(String source,String key)
    {
        MutableLiveData<NewRespone> newsData = new MutableLiveData<>();
        apiInterface.getNewsList(source,key).enqueue(new Callback<NewRespone>() {
            @Override
            public void onResponse(Call<NewRespone> call, Response<NewRespone> response) {
                if(response.isSuccessful())
                {
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NewRespone> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }

}
