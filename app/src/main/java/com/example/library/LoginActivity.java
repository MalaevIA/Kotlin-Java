package com.example.library;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin, buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Инициализация элементов
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Обработчик для кнопки "Войти"
        buttonLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Введите данные для входа", Toast.LENGTH_SHORT).show();
                return;
            }

            if (authenticateUser(email, password)) {
                goToMainActivity();
            } else {
                Toast.makeText(LoginActivity.this, "Неверный email или пароль", Toast.LENGTH_SHORT).show();
            }
        });

        // Обработчик для кнопки "Регистрация"
        buttonRegister.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Введите email и пароль для регистрации", Toast.LENGTH_SHORT).show();
                return;
            }

            if (registerUser(email, password)) {
                Toast.makeText(LoginActivity.this, "Регистрация успешна. Теперь войдите.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(LoginActivity.this, "Пользователь с таким email уже существует", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean authenticateUser(String email, String password) {
        SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String savedPassword = preferences.getString(email, null);
        return savedPassword != null && savedPassword.equals(password);
    }

    private boolean registerUser(String email, String password) {
        SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        if (preferences.contains(email)) {
            return false; // Пользователь с таким email уже существует
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(email, password);
        editor.apply();
        return true;
    }

    private void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
