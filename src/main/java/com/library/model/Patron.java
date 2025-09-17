package com.library.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Patron {
    private final String id;
    private String name;
    private final List<Book> borrowedBooks = new ArrayList<>();
    private final List<BorrowRecord> history = new ArrayList<>();

    public Patron(String id, String name) {
        this.id = Objects.requireNonNull(id);
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }
    public List<BorrowRecord> getHistory() { return history; }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
        history.add(new BorrowRecord(book.getIsbn(), BorrowRecord.Action.CHECKOUT, Instant.now()));
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
        history.add(new BorrowRecord(book.getIsbn(), BorrowRecord.Action.RETURN, Instant.now()));
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}
