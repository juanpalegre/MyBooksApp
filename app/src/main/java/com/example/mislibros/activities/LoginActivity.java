package com.example.mislibros.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mislibros.Constantes;
import com.example.mislibros.R;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail;
    EditText etContraseña;
    Button btnLogin;
    Button btnRegister;
    Toolbar toolbarLogin;
    CheckBox cbRecordar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.et_email);
        etContraseña = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);
        toolbarLogin = findViewById(R.id.toolbar_login);
        cbRecordar = findViewById(R.id.cb_recordar_credenciales);

        setSupportActionBar(toolbarLogin);
        getSupportActionBar().setTitle("Iniciar Sesión");

        verifyIfUserIsLogged();

        login();
    }

    private void login() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = etEmail.getText().toString();
                String contraseña = etContraseña.getText().toString();

                if(usuario.isEmpty() || contraseña.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Faltan ingresar datos", Toast.LENGTH_SHORT).show();
                    return;
                }

                saveUserCredentials(usuario, contraseña);

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra(Constantes.KEY_USER_EMAIL, usuario);
                intent.putExtra(Constantes.KEY_USER_PASSWORD, contraseña);
                startActivity(intent);
                finish();
            }
        });
    }

    private void saveUserCredentials(String usuario, String contraseña) {
        if (!cbRecordar.isChecked()){
            return;}
        SharedPreferences sharedPreferences = getSharedPreferences(Constantes.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        sharedPreferences.edit()
                .putString(Constantes.KEY_USER_EMAIL, usuario)
                .putString(Constantes.KEY_USER_PASSWORD, contraseña)
                .apply();
    }

    private void verifyIfUserIsLogged(){
        SharedPreferences sharedPreferences = getSharedPreferences(Constantes.CREDENTIALS_PREFERENCES, Context.MODE_PRIVATE);
        String usuario = sharedPreferences.getString(Constantes.KEY_USER_EMAIL, null);
        String contraseña = sharedPreferences.getString(Constantes.KEY_USER_PASSWORD, null);

        if(usuario != null && contraseña != null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}