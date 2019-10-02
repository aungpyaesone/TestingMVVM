package com.aa.a.reviewtalent.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.aa.a.reviewtalent.R;
import com.aa.a.reviewtalent.model.CloneData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private ArrayList<CloneData> clonedata;
    private Context context;
    public boolean isDark = false;

    public RecyclerAdapter(ArrayList<CloneData> clonedata,Context context) {
        this.clonedata = clonedata;
        this.context = context;
    }

    public RecyclerAdapter(ArrayList<CloneData> clonedata, Context context, boolean isDark) {
        this.clonedata = clonedata;
        this.context = context;
        this.isDark = isDark;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.myimg.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.mycard.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.title.setText(clonedata.get(position).getTitle());
        holder.description.setText(clonedata.get(position).getDescription());


    }

    @Override
    public int getItemCount() {
        return clonedata.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{
        public @BindView(R.id.title)
        TextView title;

        public @BindView(R.id.my_img)
        ImageView myimg;

        public @BindView(R.id.my_card)
        CardView mycard;

        public @BindView(R.id.description)
        TextView description;

        public @BindView(R.id.container)
        ConstraintLayout container;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            if(isDark)
            {
                setDarkTheme();
            }

        }

        private void setDarkTheme(){
            container.setBackgroundResource(R.drawable.card_dark_bg);
        }
    }
}
