package com.library.model;

import java.util.Objects;

public class Book {
    private final String isbn;
    private String title;
    private String author;
    private int publicationYear;
    private boolean available;

    public Book(String isbn, String title, String author, int publicationYear) {
        this.isbn = Objects.requireNonNull(isbn);
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.available = true; // default: book is available
    }

    // --- Getters & Setters ---
    public String getIsbn() { return isbn; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getPublicationYear() { return publicationYear; }
    public void setPublicationYear(int publicationYear) { this.publicationYear = publicationYear; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    // --- Overrides ---
    @Override
    public String toString() {
        return String.format("%s by %s (ISBN: %s, %d)%s",
                title, author, isbn, publicationYear, available ? "" : " [Borrowed]");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        return isbn.equals(((Book) o).isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}
