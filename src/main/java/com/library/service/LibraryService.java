package com.library.service;

import com.library.model.Book;
import com.library.model.Patron;
import com.library.search.AuthorSearch;
import com.library.search.IsbnSearch;
import com.library.search.SearchStrategy;
import com.library.search.TitleSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryService {
    private List<Book> books = new ArrayList<>();
    private List<Patron> patrons = new ArrayList<>();

    // --- Book Management ---
    public void addBook(Book b) { books.add(b); }
    public void removeBook(String isbn) {
        Book toRemove = null;
        for (Book b : books) if (b.getIsbn().equals(isbn)) toRemove = b;
        if (toRemove != null) books.remove(toRemove);
    }
    public void listAvailableBooks() {
        System.out.println("Available books:");
        for (Book b : books) if (b.isAvailable()) System.out.println(b);
    }
    public List<Book> listBorrowedBooks() {
        List<Book> res = new ArrayList<>();
        for (Book b : books) if (!b.isAvailable()) res.add(b);
        return res;
    }
    public Book findBook(String isbn) {
        for (Book b : books) if (b.getIsbn().equals(isbn)) return b;
        return null;
    }
    public void updateBook(Scanner scan) {
        System.out.print("Enter ISBN of the book to update: ");
        String isbn = scan.nextLine();
        Book book = findBook(isbn);
        if (book == null) { System.out.println("Book not found!"); return; }

        System.out.print("Enter new Title (leave blank to skip): ");
        String title = scan.nextLine(); if (!title.isEmpty()) book.setTitle(title);

        System.out.print("Enter new Author (leave blank to skip): ");
        String author = scan.nextLine(); if (!author.isEmpty()) book.setAuthor(author);

        System.out.print("Enter new Publication Year (0 to skip): ");
        int year = 0;
        try { year = Integer.parseInt(scan.nextLine()); } catch (NumberFormatException e) { year = 0; }
        if (year != 0) book.setPublicationYear(year);

        System.out.println("Book updated successfully: " + book);
    }

    // --- Patron Management ---
    public void addPatron(Patron p) { patrons.add(p); }
    public Patron findPatronById(String id) {
        for (Patron p : patrons) if (p.getId().equals(id)) return p;
        return null;
    }
    public void updatePatron(Scanner scan) {
        System.out.print("Enter Patron ID to update: ");
        String pid = scan.nextLine();
        Patron p = findPatronById(pid);
        if (p == null) { System.out.println("Patron not found!"); return; }

        System.out.print("Enter new Name (leave blank to skip): ");
        String name = scan.nextLine();
        if (!name.isEmpty()) p.setName(name);

        System.out.println("Patron updated successfully: " + p.getName());
    }

    // --- Lending ---
    public boolean checkoutBook(String isbn, String patronId) {
        Book b = findBook(isbn);
        Patron p = findPatronById(patronId);
        if (b != null && b.isAvailable() && p != null) { b.setAvailable(false); p.borrowBook(b); return true; }
        return false;
    }
    public boolean returnBook(String isbn, String patronId) {
        Patron p = findPatronById(patronId);
        if (p == null) return false;
        Book bookToReturn = null;
        for (Book b : p.getBorrowedBooks()) if (b.getIsbn().equals(isbn)) bookToReturn = b;
        if (bookToReturn != null) { bookToReturn.setAvailable(true); p.returnBook(bookToReturn); return true; }
        return false;
    }

    // --- Sub-menu Handlers ---
    public void handleInventory(Scanner scan) {
        while (true) {
            System.out.println("\n--- Inventory Management ---");
            System.out.println("1. List available books");
            System.out.println("2. List borrowed books");
            System.out.println("3. Add new book");
            System.out.println("4. Remove book");
            System.out.println("5. Update book details");
            System.out.println("0. Back to main menu");
            System.out.print("Enter choice: ");
            int task = scan.nextInt(); scan.nextLine();

            switch (task) {
                case 1: listAvailableBooks(); break;
                case 2:
                    List<Book> borrowed = listBorrowedBooks();
                    System.out.println("Borrowed books:");
                    for (Book b : borrowed) System.out.println(b);
                    break;
                case 3:
                    System.out.print("Enter ISBN: "); String isbn = scan.nextLine();
                    System.out.print("Enter Title: "); String title = scan.nextLine();
                    System.out.print("Enter Author: "); String author = scan.nextLine();
                    System.out.print("Enter Year: "); int year = Integer.parseInt(scan.nextLine());
                    addBook(new Book(isbn, title, author, year));
                    break;
                case 4:
                    System.out.print("Enter ISBN to remove: "); isbn = scan.nextLine();
                    removeBook(isbn);
                    break;
                case 5: updateBook(scan); break;
                case 0: return;
                default: System.out.println("Invalid choice"); break;
            }
        }
    }

    public void handlePatrons(Scanner scan) {
        while (true) {
            System.out.println("\n--- Patron Management ---");
            System.out.println("1. Patron history");
            System.out.println("2. Add new patron");
            System.out.println("3. Update patron details");
            System.out.println("0. Back to main menu");
            System.out.print("Enter choice: ");
            int task = scan.nextInt(); scan.nextLine();

            switch (task) {
                case 1:
                    System.out.print("Enter Patron ID: "); String pid = scan.nextLine();
                    Patron p = findPatronById(pid);
                    if (p != null) {
                        System.out.println("Borrow history:");
                        for (Object record : p.getHistory()) System.out.println(record);
                    } else System.out.println("Patron not found");
                    break;
                case 2:
                    System.out.print("Enter Patron ID: "); pid = scan.nextLine();
                    System.out.print("Enter Name: "); String pname = scan.nextLine();
                    addPatron(new Patron(pid, pname));
                    break;
                case 3: updatePatron(scan); break;
                case 0: return;
                default: System.out.println("Invalid choice"); break;
            }
        }
    }

    public void handleLending(Scanner scan) {
        while (true) {
            System.out.println("\n--- Lending ---");
            System.out.println("1. Check book availability");
            System.out.println("2. Checkout book");
            System.out.println("3. Return book");
            System.out.println("0. Back to main menu");
            System.out.print("Enter choice: ");
            int task = scan.nextInt(); scan.nextLine();

            switch (task) {
                case 1: // Check book availability
                    System.out.println("Search by: 1. ISBN 2. Title 3. Author");
                    int choice = scan.nextInt();
                    scan.nextLine(); // consume newline

                    System.out.print("Enter search keyword: ");
                    String keyword = scan.nextLine();

                    SearchStrategy strategy; // interface type
                    switch (choice) {
                        case 1: strategy = new IsbnSearch(); break;
                        case 2: strategy = new TitleSearch(); break;
                        case 3: strategy = new AuthorSearch(); break;
                        default:
                            System.out.println("Invalid choice");
                            return; // exit this case
                    }

                    // Call the search method
                    List<Book> results = strategy.search(books, keyword);

                    if (results.isEmpty()) {
                        System.out.println("No books found");
                    } else {
                        for (Book b : results) {
                            System.out.println(b + " -> " + (b.isAvailable() ? "Available" : "Borrowed"));
                        }
                    }
                    break;


                case 2:
                    String isbn;
                    System.out.print("Enter ISBN to checkout: "); isbn = scan.nextLine();
                    System.out.print("Enter Patron ID: "); String pid = scan.nextLine();
                    boolean ok = checkoutBook(isbn, pid);
                    System.out.println(ok ? "Checkout successful" : "Checkout failed");
                    break;
                case 3:
                    System.out.print("Enter ISBN to return: "); isbn = scan.nextLine();
                    System.out.print("Enter Patron ID: "); String pid2 = scan.nextLine();
                    ok = returnBook(isbn, pid2);
                    System.out.println(ok ? "Return successful" : "Return failed");
                    break;
                case 0: return;
                default: System.out.println("Invalid choice"); break;
            }
        }
    }
}
