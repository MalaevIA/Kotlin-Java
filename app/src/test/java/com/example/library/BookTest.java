package com.example.library;

import org.junit.Test;
import static org.junit.Assert.*;

public class BookTest {

    @Test
    public void testBookGettersAndSetters() {
        // Создаем книгу
        Book book = new Book("Test Title", "Test Author", "Test Content");

        // Проверяем геттеры
        assertEquals("Test Title", book.getTitle());
        assertEquals("Test Author", book.getAuthor());
        assertEquals("Test Content", book.getContent());

        // Проверяем сеттеры
        book.setTitle("New Title");
        book.setAuthor("New Author");
        book.setContent("New Content");

        assertEquals("New Title", book.getTitle());
        assertEquals("New Author", book.getAuthor());
        assertEquals("New Content", book.getContent());
    }
}
