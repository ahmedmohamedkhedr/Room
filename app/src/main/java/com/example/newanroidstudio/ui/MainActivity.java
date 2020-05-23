package com.example.newanroidstudio.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.newanroidstudio.R;
import com.example.newanroidstudio.backgroundTasks.Task;
import com.example.newanroidstudio.database.Note;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.showNotes);
        textView.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this , NotesActivity.class);
            startActivity(intent);
        });
        Button button = findViewById(R.id.saveBtn);
        EditText title = findViewById(R.id.editTitle);
        EditText note = findViewById(R.id.editNote);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        button.setOnClickListener(v -> {
            if (title.getText().toString().trim().isEmpty()) {
                title.setError("field required");
                title.requestFocus();

            } else if (note.getText().toString().trim().isEmpty()) {
                note.setError("field required");
                note.requestFocus();

            } else {
                Note noteRef = new Note(title.getText().toString() , note.getText().toString());
                Task task = new Task(progressBar , MainActivity.this , noteRef);
                task.execute();
            }
        });

    }
}