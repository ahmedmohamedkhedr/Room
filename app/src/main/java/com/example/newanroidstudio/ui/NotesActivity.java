package com.example.newanroidstudio.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.newanroidstudio.R;
import com.example.newanroidstudio.database.DatabaseClient;
import com.example.newanroidstudio.database.Note;

import java.util.List;

public class NotesActivity extends AppCompatActivity implements NotesAdapter.OnItemClickListener {
    private NotesAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        recyclerView = findViewById(R.id.notesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        new AsyncTask<Void, Void, List<Note>>() {
            @Override
            protected List<Note> doInBackground(Void... voids) {
                List<Note> notes = DatabaseClient.getInstance(NotesActivity.this).getDatabase().getDAO().getNotes();

                return notes;
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                super.onPostExecute(notes);
                adapter = new NotesAdapter(notes , NotesActivity.this , NotesActivity.this);
                recyclerView.setAdapter(adapter);

            }
        }.execute();

    }

    @Override
    public void onItemClicked(int id, Note note) {
        Intent intent = new Intent(this , ShowNoteActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("note",note);
        startActivity(intent);
    }


}