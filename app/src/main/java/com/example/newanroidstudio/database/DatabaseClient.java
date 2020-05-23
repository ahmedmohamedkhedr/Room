package com.example.newanroidstudio.database;

import android.content.Context;

import androidx.room.Room;

public class DatabaseClient {
    private NoteDatabase database;
    private static DatabaseClient client;


    private DatabaseClient(Context context) {
        database = Room.databaseBuilder(context, NoteDatabase.class, "Note.dp").build();

    }

    public static synchronized DatabaseClient getInstance(Context context) {
        if (client == null)
            client = new DatabaseClient(context);
        return client;

    }

    public NoteDatabase getDatabase() {
        return database;
    }


}
