package com.aa.a.reviewtalent.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.aa.a.reviewtalent.database.Note;
import com.aa.a.reviewtalent.database.NoteRepository;

import java.util.List;

public class ViewModelExampleViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public ViewModelExampleViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Note note)
    {
        repository.insert(note);
    }
    public void update(Note note)
    {
        repository.update(note);

    }

    public void delete(Note note)
    {
        repository.delete(note);
    }

    public void deletAllNotes()
    {
        repository.deleteAllNotes();
    }

   public LiveData<List<Note>> getAllNotes()
   {
       return allNotes;
   }
    // TODO: Implement the ViewModel
}
