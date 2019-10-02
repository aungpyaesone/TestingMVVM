package com.aa.a.reviewtalent.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note)
    {
        new InsertNoteAsynTask(noteDao).execute(note);
    }

    public void update(Note note)
    {
        new UpdateNoteAsynTask(noteDao).execute(note);
    }
    public void delete(Note note)
    {
        new DeleteNoteAsynTask(noteDao).execute(note);
    }
    public void deleteAllNotes(){
        new DeleteAlltNoteAsynTask(noteDao).execute();
    }
    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    private static class InsertNoteAsynTask extends AsyncTask<Note,Void,Void>
    {
        private NoteDao noteDao;
        public InsertNoteAsynTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsynTask extends AsyncTask<Note,Void,Void>
    {
        private NoteDao noteDao;

        public UpdateNoteAsynTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsynTask extends AsyncTask<Note,Void,Void>
    {
        private NoteDao noteDao;

        public DeleteNoteAsynTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAlltNoteAsynTask extends AsyncTask<Note,Void,Void>
    {
        private NoteDao noteDao;

        public DeleteAlltNoteAsynTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.deleteAllNotes();
            return null;
        }
    }




}
