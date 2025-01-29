package com.example.library;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

public class ReadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            String name = getIntent().getStringExtra("title");
            if(name != null){
                actionBar.setTitle(name);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            else {
                actionBar.setTitle("Нет названия");
            }
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        TextView contentTextView = findViewById(R.id.contentTextView);
        String content = getIntent().getStringExtra("content");
        if (content != null) {
            contentTextView.setText(content);
        } else {
            contentTextView.setText("Контент отсутствует");
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Обработка нажатия кнопки "Назад"
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Вернуться к предыдущей активности
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
