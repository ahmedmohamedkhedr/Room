package com.example.newanroidstudio.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDAO {
    @Insert
    void insertNote(Note note);

    @Query("SELECT * FROM note")
    List<Note> getNotes();

    @Query("SELECT * from note WHERE id LIKE :id")
    Note getNote(int id);

    @Query("UPDATE note SET title = :title, note = :note WHERE id = :id ")
    void updateNote(String title , String note , int id);

    @Delete
    void deleteNote(Note note);
}
