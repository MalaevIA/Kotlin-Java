package com.example.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> books;
    private OnBookClickListener onBookClickListener;
    public BookAdapter(List<Book> books, OnBookClickListener listener) {
        this.books = books;
        this.onBookClickListener = listener;
    }
    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = books.get(position);
        holder.bookTitle.setText(book.getTitle());
        holder.bookAuthor.setText(book.getAuthor());
        // Обработка нажатий на элемент
        holder.itemView.setOnClickListener(v -> {
            try {
                onBookClickListener.onBookClick(book);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        // Обработка удаления книги
        holder.itemView.findViewById(R.id.buttonDelete).setOnClickListener(v -> {
            onBookClickListener.onDeleteClick(book);
        });
    }
    @Override
    public int getItemCount() {
        return books.size();
    }
    static class BookViewHolder extends RecyclerView.ViewHolder {
        TextView bookTitle, bookAuthor;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookAuthor = itemView.findViewById(R.id.bookAuthor);
        }
    }
    public interface OnBookClickListener {
        void onBookClick(Book book) throws IOException;
        void onDeleteClick(Book book);
    }
}
