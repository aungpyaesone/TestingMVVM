package com.aa.a.reviewtalent.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aa.a.reviewtalent.R;
import com.aa.a.reviewtalent.model.ArticlesItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.MyViewHolder> {

    private ArrayList<ArticlesItem> articlesItems;
    private Context context;
    //LayoutInflater layoutInflater;

    public NewsRecyclerAdapter(ArrayList<ArticlesItem> articlesItems,Context context) {
        this.articlesItems = articlesItems;
        this.context = context;
       // layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_view,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("test_data", "position = " + position);
        holder.newsTitle.setText(articlesItems.get(position).getTitle());
        holder.author_name.setText(articlesItems.get(position).getAuthor());
        holder.newsDescription.setText(articlesItems.get(position).getDescription());

    }

    public void bindData(ArrayList<ArticlesItem> articlesItems) {
        this.articlesItems = articlesItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return articlesItems.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public @BindView(R.id.title)
        TextView newsTitle;
        public @BindView(R.id.author_name)
        TextView author_name;
        public @BindView(R.id.description)
        TextView newsDescription;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
