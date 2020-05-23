package com.example.newanroidstudio.backgroundTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.newanroidstudio.database.DatabaseClient;
import com.example.newanroidstudio.database.Note;

public class Task extends AsyncTask<Void, Void, Void> {
    private ProgressBar progressBar;
    private Context context;
    private Note note;

    public Task(ProgressBar progressBar, Context context , Note note) {
        this.progressBar = progressBar;
        this.context = context;
        this.note = note;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        DatabaseClient.getInstance(context).getDatabase().getDAO().insertNote(note);
        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(context, "Done", Toast.LENGTH_LONG).show();
        }
    }
}
