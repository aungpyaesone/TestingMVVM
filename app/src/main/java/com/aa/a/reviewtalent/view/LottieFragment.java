package com.aa.a.reviewtalent.view;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aa.a.reviewtalent.Adapter.NewsRecyclerAdapter;
import com.aa.a.reviewtalent.R;
import com.aa.a.reviewtalent.model.ArticlesItem;
import com.aa.a.reviewtalent.model.NewRespone;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LottieFragment extends Fragment {

    private LottieViewModel mViewModel;

   /* public @BindView(R.id.editText)
    EditText something;

    public @BindView(R.id.btn_send)
    Button send;

    public @BindView(R.id.textView)
    TextView textView;*/

   public @BindView(R.id.news_recycler)
   RecyclerView newsRecyclerView;

   public ArrayList<ArticlesItem> articles = new ArrayList<>();

   private LinearLayoutManager manager;
   private NewsRecyclerAdapter adapter;

    public static LottieFragment newInstance() {
        return new LottieFragment();
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        Log.i("OnAttach","On attach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("onCreate","onCreate");
    }


    // public   MyData Data;

    private void setUpRecyclerView(){
        adapter = new NewsRecyclerAdapter(articles,getActivity());
        manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        newsRecyclerView.setLayoutManager(manager);
        newsRecyclerView.setAdapter(adapter);
     /*   if(adapter == null) {
            Log.d("test_data", "adapter is null");
            adapter = new NewsRecyclerAdapter(articles,getActivity());
            manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            newsRecyclerView.setLayoutManager(manager);
            newsRecyclerView.setAdapter(adapter);
            Log.i("Ok Ok Ok",adapter.toString());

        }else
        {
            Log.d("test_data", "adapter is created");
            Log.d("test_data", "new size = " + articles.size());
            adapter.notifyDataSetChanged();
            //adapter.bindData(articles);
        }*/
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
      //  Data = (MyData) getActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lottie_fragment, container, false);
        ButterKnife.bind(this,v);
        Log.i("OnCreateView","OnCreateView");
        Log.d("test_data", "onCreateView");
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("onActivityCreated","onActivityCreated");
        Log.d("test_data", "onActivitedCreated");
        mViewModel = ViewModelProviders.of(this).get(LottieViewModel.class);
        mViewModel.init();
        mViewModel.getNewsRepository().observe(this, new Observer<NewRespone>() {
                    @Override
                    public void onChanged(NewRespone newRespone) {
                        List<ArticlesItem> articlesItems = newRespone.getArticles();
                        articles= (ArrayList<ArticlesItem>) articlesItems;
                        adapter.bindData(articles);
                        //adapter.notifyDataSetChanged();
                        Log.d("test_data", "size = " + articles.size());
                        Log.i("What data can we see",articles.toString());
                        Toast.makeText(getActivity(), "what Change", Toast.LENGTH_SHORT).show();
                    }
                });
        setUpRecyclerView();

        // TODO: Use the ViewModel
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("onStart","onStart");
        Log.d("test_data", "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("on Resume adapter",adapter.toString());
        Log.i("onResume","onResume");
        Log.d("test_data", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("on Pause adapter",adapter.toString());
        Log.i("onPause","onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("onStop","onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy","onDestroy");
        Log.d("test_data", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("onDetach","onDetach");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("onViewCreated","onViewCreated");
      /*  send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data.sendData(something.getText().toString().trim());
            }
        });*/
    }

    public interface MyData{
        void sendData(String message);
    }

}
