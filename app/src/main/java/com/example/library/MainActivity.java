package com.example.library;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            String title = "Библиотека";
            actionBar.setTitle(title);
        }
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        // Устанавливаем слушатель для обработки нажатий
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            if (item.getItemId() == R.id.nav_books) {
                selectedFragment = new BookListFragment();
            } else if (item.getItemId() == R.id.nav_download) {
                selectedFragment = new AddBookFragment();
            }
            if (selectedFragment != null) {
                loadFragment(selectedFragment);
            }
            return true; // Указываем, что нажатие обработано
        });
        // Загружаем первый фрагмент при запуске
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_books);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_person, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about_user) {
            loadFragment(new AboutFragment());
            return true;
        } else if (id == R.id.action_about_developer) {
            loadFragment(new DeveloperFragment());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }
}


