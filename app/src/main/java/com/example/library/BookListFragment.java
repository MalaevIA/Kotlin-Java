package com.example.library;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookListFragment extends Fragment {
    RecyclerView recyclerView;
    BookAdapter adapter;
    protected List<Book> books;
    List<Book> filteredBooks;
    private EditText searchEditText;
    public BookListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        searchEditText = view.findViewById(R.id.searchEditText);
        // Загружаем книги из базы данных
        loadBooks();
        // Обработчик для поиска
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                filterBooks(charSequence.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
        return view;
    }
    private void loadBooks() {
        AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "database-name").build();
        // Запускаем фоновый поток для загрузки книг
        new Thread(() -> {
            books = db.bookDao().getAllBooks();
            filteredBooks = new ArrayList<>(books);  // Изначально все книги отображаются
            getActivity().runOnUiThread(() -> {
                adapter = new BookAdapter(filteredBooks, new BookAdapter.OnBookClickListener() {
                    @Override
                    public void onBookClick(Book book) {
                        // Обработка нажатия на книгу
                        Toast.makeText(getContext(), "Открываем: " + book.getTitle(), Toast.LENGTH_SHORT).show();
                        openBook(book);
                    }
                    @Override
                    public void onDeleteClick(Book book) {
                        // Удаление книги
                        deleteBookFromDatabase(book);
                    }
                });
                recyclerView.setAdapter(adapter);
            });
        }).start();
    }
    void filterBooks(String query) {
        List<Book> filteredList = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(book);
            }
        }
        filteredBooks.clear();
        filteredBooks.addAll(filteredList);
        adapter.notifyDataSetChanged();  // Обновляем адаптер
    }
    private void openBook(Book book) {
        Intent intent = new Intent(getContext(), ReadActivity.class);
        intent.putExtra("content", book.getContent());
        intent.putExtra("title",book.getTitle());
        startActivity(intent);
    }
    private void deleteBookFromDatabase(Book book) {
        AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "database-name").build();
        // Запуск удаления книги в фоновом потоке
        new Thread(() -> {
            db.bookDao().delete(book);
            getActivity().runOnUiThread(() -> {
                // Удаляем книгу из списка и уведомляем адаптер
                books.remove(book);
                filteredBooks.remove(book);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Книга удалена", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }
}