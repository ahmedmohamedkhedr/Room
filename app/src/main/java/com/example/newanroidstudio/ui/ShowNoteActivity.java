package com.example.newanroidstudio.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newanroidstudio.R;
import com.example.newanroidstudio.database.DatabaseClient;
import com.example.newanroidstudio.database.Note;

public class ShowNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note);
        TextView title = findViewById(R.id.title);
        TextView note = findViewById(R.id.note);
        Button buttonDelete = findViewById(R.id.deleteBtn);
        Button buttonUpdate = findViewById(R.id.updateBtn);
        LinearLayout linearLayout = findViewById(R.id.updateContainer);
        EditText newTitle = findViewById(R.id.updatedTitle);
        EditText newNote = findViewById(R.id.updatedNote);
        Button submit = findViewById(R.id.submitUpdate);
        int id = getIntent().getIntExtra("id", 0);
        Note intentNote = (Note) getIntent().getSerializableExtra("note");

        new AsyncTask<Void, Void, Note>() {

            @Override
            protected Note doInBackground(Void... voids) {
                Note note = DatabaseClient.getInstance(ShowNoteActivity.this).getDatabase().getDAO().getNote(id);
                return note;
            }

            @Override
            protected void onPostExecute(Note note_) {
                super.onPostExecute(note_);
                title.setText(note_.getTitle());
                note.setText(note_.getNote());

            }
        }.execute();

        buttonDelete.setOnClickListener(v -> {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    DatabaseClient.getInstance(ShowNoteActivity.this).getDatabase().getDAO().deleteNote(intentNote);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast.makeText(ShowNoteActivity.this, "Done", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }.execute();
        });

        buttonUpdate.setOnClickListener(v -> {
            linearLayout.setVisibility(View.VISIBLE);
        });

        submit.setOnClickListener(v -> {
            String updatedTitle = newTitle.getText().toString().trim();
            String updatedNote = newNote.getText().toString().trim();

            if (TextUtils.isEmpty(updatedTitle)) {
                newTitle.setError("this field required");
                newTitle.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(updatedNote)) {
                newNote.setError("this field required");
                newNote.requestFocus();
                return;
            }
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    DatabaseClient.getInstance(ShowNoteActivity.this).getDatabase().getDAO().updateNote(updatedTitle, updatedNote, id);
                    return null;
                }

                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast.makeText(ShowNoteActivity.this, "Note Updated", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }.execute();
        });
    }
}