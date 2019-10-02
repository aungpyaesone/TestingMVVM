package com.aa.a.reviewtalent.view;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aa.a.reviewtalent.R;
import com.airbnb.lottie.LottieAnimationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AcceptData extends Fragment {

    private AcceptDataViewModel mViewModel;
    public @BindView(R.id.txt_receive)
    TextView txtReceive;
    public @BindView(R.id.user_connection)
    LottieAnimationView lottieAnimationView;


    public static AcceptData newInstance() {
        return new AcceptData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.accept_data_fragment, container, false);
        ButterKnife.bind(this,v);
      //  lottieAnimationView.playAnimation();
        lottieAnimationView.setScale((float) 0.5);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AcceptDataViewModel.class);
        // TODO: Use the ViewModel
    }

    public void displayReceiveData(String message)
    {
        txtReceive.setText(message);
    }

    @Override
    public void onPause() {
        super.onPause();
       lottieAnimationView.pauseAnimation();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        lottieAnimationView.cancelAnimation();
    }
}
