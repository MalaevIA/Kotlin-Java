package com.example.library;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class BookListFragmentTest {

    private BookListFragment bookListFragment;

    @Before
    public void setUp() {
        bookListFragment = new BookListFragment();
        bookListFragment.books = new ArrayList<>();
        bookListFragment.books.add(new Book("Harry Potter", "J.K. Rowling", "Fantasy novel"));
    }

    @Test
    public void testBookListSize() {
        assertEquals(1, bookListFragment.books.size());
    }

    @Test
    public void testAddBook() {
        bookListFragment.books.add(new Book("The Hobbit", "J.R.R. Tolkien", "Fantasy"));
        assertEquals(2, bookListFragment.books.size());
    }
}









