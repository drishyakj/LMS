# Library Management System (LMS)

A simple **Library Management System** in Java demonstrating OOP concepts, SOLID principles, and the Strategy design pattern for search functionality.

---

## Features

### Book Management
- Add, remove, and update books.
- List available and borrowed books.

### Patron Management
- Add new patrons and update their information.
- View borrowing history for each patron.

### Lending Process
- Check book availability.
- Checkout books to patrons.
- Return books from patrons.

### Search Functionality
- Search books by **ISBN**, **Title**, or **Author** using the **Strategy Pattern**.

### Console Menu
- Menu-driven interface with separate handlers for:
    - Inventory Management
    - Patron Management
    - Lending Process

---

## Design Overview

- **Packages**
    - `com.library` – Main class to run the application.
    - `com.library.model` – Contains `Book` and `Patron` classes.
    - `com.library.service` – Core library service with book, patron, and lending management.
    - `com.library.search` – Search strategies for ISBN, Title, and Author.
- **OOP Concepts**
    - Encapsulation: Classes with private fields and getters/setters.
    - Interface: `SearchStrategy` for flexible search implementation.
    - Modular design with separate handlers for each functionality.
- **Design Pattern**
    - **Strategy Pattern** for book search.

---

## Sample Class Diagram

```
          +-----------------+
          |      Book       |
          +-----------------+
          | - isbn:String   |
          | - title:String  |
          | - author:String |
          | - year:int      |
          | - borrowed:bool |
          +-----------------+
          | + getters/setters|
          +-----------------+

          +-----------------+
          |     Patron      |
          +-----------------+
          | - id:String     |
          | - name:String   |
          | - borrowedBooks |
          | - history       |
          +-----------------+
          | + getters/setters|
          +-----------------+

          +-----------------+
          | LibraryService  |
          +-----------------+
          | - books:List    |
          | - patrons:List  |
          +-----------------+
          | + handleInventory(scan) |
          | + handlePatrons(scan)   |
          | + handleLending(scan)   |
          +-----------------+

          +-----------------+
          | SearchStrategy  |<----+
          +-----------------+     |
          | + search(...)   |     |
          +-----------------+     |
                                   
          +-----------------+    +-----------------+    +-----------------+
          |  IsbnSearch     |    | TitleSearch     |    | AuthorSearch    |
          +-----------------+    +-----------------+    +-----------------+
          | + search(...)   |    | + search(...)   |    | + search(...)   |
          +-----------------+    +-----------------+    +-----------------+
```

---

## Getting Started

### Prerequisites
- Java JDK 8 or above
- Maven (optional, if using as a Maven project)
- IDE: IntelliJ, Eclipse, or any Java-supporting editor

### Running the Application
1. Clone the repository:
   ```bash
   git clone git@github.com:your-username/LMS.git
   cd LMS
   ```
2. Compile and run:
   ```bash
   javac <filepath.java>>
   java Main
   ```
3. Follow the **console menu** to manage books, patrons, and lending.

---

## Usage Example

```
--- Welcome to DJ's Library ---
1. Inventory management
2. Patron management
3. Lending
0. Exit
Enter your choice: 1

--- Inventory Management ---
1. List available books
2. List borrowed books
3. Add new book
4. Remove book
5. Update book details
0. Back to main menu
Enter choice: 1
```

- Users can interactively add books, register patrons, checkout, return, and search.

---

## Future Improvements
- Integrate database persistence (MySQL, PostgreSQL, etc.).
- Implement GUI using JavaFX or Swing.
- Add authentication for librarians.
- Implement reservation system.
- Implement recommendation system.
- Multibranch support.

---

## License
This project is open-source and free to use.
