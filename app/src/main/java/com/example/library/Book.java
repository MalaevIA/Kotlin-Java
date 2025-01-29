package com.example.library;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "books")
public class Book {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String author;
    private String content;

    // Конструктор, геттеры и сеттеры
    public Book(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() { return content; }

    public void setContent(String content) {
        this.content = content;
    }
}
