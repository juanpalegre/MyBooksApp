package com.example.mislibros.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mislibros.Constantes;
import com.example.mislibros.R;
import com.example.mislibros.adapters.LibrosAdapter;
import com.example.mislibros.adapters.OnItemClickListener;
import com.example.mislibros.database.LibrosDao;
import com.example.mislibros.database.LocalDatabase;
import com.example.mislibros.modelos.Libro;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnItemClickListener<Libro> {

    private RecyclerView rvLibros;
    private Toolbar toolbarMain;
    private LibrosAdapter librosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String usuario = bundle.getString(Constantes.KEY_USER_EMAIL);
            Toast.makeText(this, "Bienvenido " + usuario, Toast.LENGTH_SHORT).show();
        }

        rvLibros = findViewById(R.id.rv_libros);
        toolbarMain = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setTitle("Mi lista de libros");

        librosAdapter = new LibrosAdapter(proveerLibros());
        librosAdapter.setOnItemClickListener(this);
        //librosAdapter.setOnLibroClickListener(this);

        rvLibros.setAdapter(librosAdapter);
        rvLibros.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LocalDatabase database = LocalDatabase.getInstance(this);
        LibrosDao librosDao = database.getLibrosDao();
        List<Libro> libros = librosDao.getAll();

        librosAdapter.update(libros);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add){
            Intent intent = new Intent(MainActivity.this, AgregarLibroActivity.class);
            startActivity(intent);
        } else if ( item.getItemId() == R.id.action_cerrar_sesion){
            Toast.makeText(this, "Cerrando Sesión", Toast.LENGTH_SHORT).show();
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences(Constantes.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private List<Libro> proveerLibros(){
        /*Libro libro1 = new Libro("El señor de los anillos", "Tolkien");
        Libro libro2 = new Libro("Juan Salvador Gaviota", "Richard Bach");
        Libro libro3 = new Libro("ANDROID", "José Dimas Luján Castillo");
        Libro libro4 = new Libro("Ilusiones", "Richard Bach");
        Libro libro5 = new Libro("El caballero de la armadura oxidada", "Robert Fisher");

        List<Libro> libros = new ArrayList<>();
        libros.add(libro1);
        libros.add(libro2);
        libros.add(libro3);
        libros.add(libro4);
        libros.add(libro5);
        libros.add(libro1);
        libros.add(libro2);
        libros.add(libro1);
        libros.add(libro2);
        libros.add(libro3);
        libros.add(libro4);
        libros.add(libro5);
        libros.add(libro1);
        libros.add(libro2);
        */
        LocalDatabase database = LocalDatabase.getInstance(this);
        LibrosDao librosDao = database.getLibrosDao();
        List<Libro> libros = librosDao.getAll();

        return libros;
    }

    @Override
    public void onItemClick(Libro item) {
        Toast.makeText(this, "Título: " + item.getNombre(), Toast.LENGTH_SHORT).show();
    }

    /*@Override
    public void onLibroClick(Libro libro) {
        Toast.makeText(this, "Titulo: " + libro.getNombre(), Toast.LENGTH_SHORT).show();
    }*/
}