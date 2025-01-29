package com.example.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

public class AddBookFragment extends Fragment {
    private EditText titleEditText, authorEditText, contentEditText;
    public AddBookFragment() {
        // Требуется пустой конструктор
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);
        titleEditText = view.findViewById(R.id.titleEditText);
        authorEditText = view.findViewById(R.id.authorEditText);
        contentEditText = view.findViewById(R.id.contentEditText);
        Button addBookButton = view.findViewById(R.id.addBookButton);
        // Обработчик кнопки для добавления книги
        addBookButton.setOnClickListener(v -> addBook());
        return view;
    }
    private void addBook() {
        // Получаем введенные данные
        String title = titleEditText.getText().toString();
        String author = authorEditText.getText().toString();
        String content = contentEditText.getText().toString();
        if (title.isEmpty() || author.isEmpty() || content.isEmpty()) {
            Toast.makeText(getContext(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
            return;
        }
        // Сохраняем данные в базу
        saveBookToDatabase(title, author, content);
    }
    private void saveBookToDatabase(String title, String author, String content) {
        // Инициализируем базу данных
        AppDatabase db = Room.databaseBuilder(getContext(),
                AppDatabase.class, "database-name").build();
        Book newBook = new Book(title, author, content);
        // Запуск операции в фоновом потоке
        new Thread(() -> {
            db.bookDao().insert(newBook);
            getActivity().runOnUiThread(() -> Toast.makeText(getContext(), "Книга добавлена", Toast.LENGTH_SHORT).show());
        }).start();
    }
}
