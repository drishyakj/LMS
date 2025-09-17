package com.library;

import com.library.model.Book;
import com.library.model.Patron;
import com.library.service.LibraryService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        LibraryService library = new LibraryService();

        // Sample data
        library.addBook(new Book("1111111111111", "Book1", "Author1", 2011));
        library.addBook(new Book("2222222222222", "Book2", "Author2", 2012));
        library.addBook(new Book("3333333333333", "Book3", "Author3", 2013));

        library.addPatron(new Patron("pat1", "Drish"));
        library.addPatron(new Patron("pat2", "Dia"));

        while (true) {
            System.out.println("\n--- Welcome to DJ's Library ---");
            System.out.println("1. Inventory management");
            System.out.println("2. Patron management");
            System.out.println("3. Lending");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scan.nextInt();
            scan.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    library.handleInventory(scan);
                    break;
                case 2:
                    library.handlePatrons(scan);
                    break;
                case 3:
                    library.handleLending(scan);
                    break;
                case 0:
                    System.out.println("Thank you. Have a nice day :)");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
                    break;
            }
        }
    }
}
