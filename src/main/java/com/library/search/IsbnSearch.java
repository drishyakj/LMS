package com.library.search;

import com.library.model.Book;
import java.util.*;

public class IsbnSearch implements SearchStrategy {
    @Override
    public List<Book> search(Collection<Book> books, String query) {
        List<Book> res = new ArrayList<>();
        if (query == null) return res;
        for (Book b : books) if (b.getIsbn().equals(query)) res.add(b);
        return res;
    }
}
