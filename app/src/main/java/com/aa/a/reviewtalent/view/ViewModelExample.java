package com.aa.a.reviewtalent.view;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.aa.a.reviewtalent.Adapter.MVRecyclerAdapter;
import com.aa.a.reviewtalent.AddActivity;
import com.aa.a.reviewtalent.R;
import com.aa.a.reviewtalent.database.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;

public class ViewModelExample extends Fragment {

    private ViewModelExampleViewModel mViewModel;

    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    public @BindView(R.id.mv_recycler)
    RecyclerView recyclerView;

    public @BindView(R.id.fab_btn)
    FloatingActionButton fabButton;

    public static ViewModelExample newInstance() {
        return new ViewModelExample();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.view_model_example_fragment, container, false);
        ButterKnife.bind(this,v);
        setHasOptionsMenu(true);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        final MVRecyclerAdapter adapter = new MVRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivityForResult(intent,1);
            }
        });

        mViewModel = ViewModelProviders.of(this).get(ViewModelExampleViewModel.class);
        mViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //adapter.setNotes(notes);
                adapter.submitList(notes);
                Toast.makeText(getActivity(), "Change", Toast.LENGTH_SHORT).show();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mViewModel.delete(adapter.getNoteIndex(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(),"Note Deleted",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setItemClickListener(new MVRecyclerAdapter.OnItemClickListener() {
            @Override
            public void ItemClick(Note note) {
                Intent i = new Intent(getActivity(),AddActivity.class);
                i.putExtra(AddActivity.EXTRA_ID,note.getId());
                i.putExtra(AddActivity.EXTRA_TITLE,note.getTitle());
                i.putExtra(AddActivity.EXTRA_DESCRIPTION,note.getDescription());
                i.putExtra(AddActivity.EXTRA_PRIORITY,note.getPriority());
                startActivityForResult(i,EDIT_NOTE_REQUEST);
            }
        });


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.delete_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.delete:
                mViewModel.deletAllNotes();
                Toast.makeText(getActivity(), "Delete All Note Successful", Toast.LENGTH_SHORT).show();
                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_NOTE_REQUEST && resultCode==RESULT_OK)
        {
            String title = data.getStringExtra(AddActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddActivity.EXTRA_PRIORITY,1);

            Note note = new Note(title,description,priority);
            mViewModel.insert(note);
            Toast.makeText(getActivity(), "Note Saved", Toast.LENGTH_SHORT).show();
        }
        else if(requestCode == EDIT_NOTE_REQUEST && resultCode==RESULT_OK){
            int id = data.getIntExtra(AddActivity.EXTRA_ID,-1);
            Log.d("show id",String.valueOf(id));
            if(id == -1)
            {
                Toast.makeText(getActivity(), "Can't update note", Toast.LENGTH_SHORT).show();
            }
            String title = data.getStringExtra(AddActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddActivity.EXTRA_PRIORITY,1);
            Note note = new Note(title,description,priority);
            note.setId(id);
            mViewModel.update(note);
            Toast.makeText(getActivity(), "update successful", Toast.LENGTH_SHORT).show();
        }

        else{
            Toast.makeText(getActivity(), "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
