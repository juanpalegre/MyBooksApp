package com.example.mislibros.modelos;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Libros")
public class Libro {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nombre;
    private String autor;

    public Libro(int id, String nombre, String autor) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
    }

    public Libro(){

    }

    public Libro(String nombre, String autor) {
        this.nombre = nombre;
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
