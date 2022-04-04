package com.example.mislibros.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mislibros.modelos.Libro;

import java.util.List;

@Dao
public interface LibrosDao {

    @Query("SELECT * FROM Libros")
    List<Libro> getAll();

    @Query("SELECT * FROM Libros WHERE id = :id")
    Libro get(int id);

    @Insert
    void insert(Libro libro);

    @Update
    void set(Libro libro);

    @Delete
    void delete(Libro libro);

}
