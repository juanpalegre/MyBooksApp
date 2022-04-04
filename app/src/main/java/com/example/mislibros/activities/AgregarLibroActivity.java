package com.example.mislibros.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mislibros.R;
import com.example.mislibros.database.LibrosDao;
import com.example.mislibros.database.LocalDatabase;
import com.example.mislibros.modelos.Libro;

public class AgregarLibroActivity extends AppCompatActivity {

    private EditText etTitulo;
    private EditText etAutor;
    private Button btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_libro);

        etTitulo = findViewById(R.id.et_titulo);
        etAutor = findViewById(R.id.et_autor);
        btnAgregar = findViewById(R.id.btn);

        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar_agregar_libro);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.agregar_libro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarLibro();
            }
        });
    }

    private void guardarLibro() {
        String titulo = etTitulo.getText().toString();
        String autor = etAutor.getText().toString();

        Libro nuevoLibro = new Libro(titulo, autor);

        LocalDatabase localDatabase = LocalDatabase.getInstance(this);
        LibrosDao librosDao = localDatabase.getLibrosDao();
        librosDao.insert(nuevoLibro);

        Toast.makeText(this, "Libro guardado", Toast.LENGTH_SHORT).show();
        etTitulo.setText("");
        etAutor.setText("");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}