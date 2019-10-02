package com.aa.a.reviewtalent.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.aa.a.reviewtalent.R;
import com.aa.a.reviewtalent.database.Note;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MVRecyclerAdapter extends ListAdapter<Note,MVRecyclerAdapter.MyViewHolder> {
//RecyclerView.Adapter<MVRecyclerAdapter.MyViewHolder>
   // private List<Note> notes = new ArrayList<>();
    public OnItemClickListener itemClickListener;

    public MVRecyclerAdapter()
    {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId()== newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())&&
                    oldItem.getDescription().equals(newItem.getDescription())&&
                    oldItem.getPriority()==newItem.getPriority();
        }
    };


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.mvvm_item_view,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Note note = getItem(position);
        holder.title.setText(note.getTitle());
        holder.description.setText(note.getDescription());
        holder.priority.setText(String.valueOf(note.getPriority()));

    }

  /*  @Override
    public int getItemCount() {
        return notes.size();
    }
*/
    public Note getNoteIndex(int position)
    {
        //notes.get(position);
        return getItem(position);
    }

   /* public void setNotes(List<Note> note)
    {
        this.notes = note;
        notifyDataSetChanged();
    }
*/
    public class MyViewHolder extends RecyclerView.ViewHolder{

    public @BindView(R.id.title)
    TextView title;
    public @BindView(R.id.description)
    TextView description;
    public @BindView(R.id.priority)
    TextView priority;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();
                itemClickListener.ItemClick(getItem(position));
                //notes.get(position)
            }
        });
    }
}
public interface OnItemClickListener{
        void ItemClick(Note note);
}

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
}
