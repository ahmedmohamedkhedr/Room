package com.example.newanroidstudio.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Note implements Serializable {
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "note")
    private String note;
    @PrimaryKey(autoGenerate = true)
     int id = 0;

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }

    public int getId() {
        return id;
    }



    public Note(String title, String note) {
        this.title = title;
        this.note = note;
    }
}
