package com.aa.a.reviewtalent.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aa.a.reviewtalent.Network.NewRepository;
import com.aa.a.reviewtalent.model.NewRespone;

// This is NewsViewsModel

public class LottieViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<NewRespone> mutableLiveData;
    private NewRepository newRepository;

    public LottieViewModel(@NonNull Application application) {
        super(application);
        newRepository = new NewRepository();
        init();

    }

    public void init(){
        if(mutableLiveData != null)
        {
            return;
        }
        newRepository = NewRepository.getInstance();
        mutableLiveData = newRepository.getNews("techcrunch","0eb7df2a952649ef88f56b6bbc617a65");
    }

    public LiveData<NewRespone> getNewsRepository(){
        return mutableLiveData;
    }
}
