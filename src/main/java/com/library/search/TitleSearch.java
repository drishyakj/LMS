package com.library.search;

import com.library.model.Book;
import java.util.*;

public class TitleSearch implements SearchStrategy {
    @Override
    public List<Book> search(Collection<Book> books, String query) {
        List<Book> res = new ArrayList<>();
        if (query == null) return res;
        String q = query.toLowerCase();
        for (Book b : books) if (b.getTitle() != null && b.getTitle().toLowerCase().contains(q)) res.add(b);
        return res;
    }
}
