package com.library.model;

import java.time.Instant;

public class BorrowRecord {
    public enum Action { CHECKOUT, RETURN }

    private final String isbn;
    private final Action action;
    private final Instant timestamp;

    public BorrowRecord(String isbn, Action action, Instant timestamp) {
        this.isbn = isbn;
        this.action = action;
        this.timestamp = timestamp;
    }

    public String getIsbn() { return isbn; }
    public Action getAction() { return action; }
    public Instant getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return String.format("[%s] %s %s", timestamp, action, isbn);
    }
}
