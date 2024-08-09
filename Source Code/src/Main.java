//import Controllers.BookController;
//import Controllers.UserController;
//import Models.Book;
//import Models.User;
//import Services.BookService;
//import Services.UserService;
//
//import java.sql.*;
//import java.util.List;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//
//        String sql = "SELECT * FROM Users";
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=bookstoreDB;user=sa;password=123;encrypt=true;trustServerCertificate=true;";
//        String username = "sa";
//        String password = "123";
//
//        // Register ok works correctly.
//        Scanner scanner = new Scanner(System.in);
//
////        System.out.println("Enter name:");
////        String name = scanner.nextLine();
////
////        System.out.println("Enter username:");
////        String user = scanner.nextLine();
////
////        System.out.println("Enter password:");
////        String pwd = scanner.nextLine();
////
////        User user1 = new User(name, user, password);
////        UserService us = new UserService();
////        UserController UC = new UserController(us);
////        UC.registerUser(user1);
//
//        UserService userService = new UserService();
////        UserController uc = new UserController(userService);
//
//        /// Login ok works correctly
////        System.out.println("Enter username: ");
////        String user = scanner.nextLine();
////
////        System.out.println("Enter password");
////
////        String pwd = scanner.nextLine();
////
////        uc.loginUser(user,pwd);
////
////        scanner.close();
//
////        System.out.println("            Browse and Search Books:        ");
////        System.out.println("        ------------------------------      ");
////        System.out.println("        ------------------------------      ");
////        System.out.println("1. Search by author.");
////        System.out.println("2. Search by title.");
////        System.out.println("3. Search by genre.");
//
////        int choice;
////        choice = scanner.nextInt();
//
//
//         //create book
//        System.out.println("Create Book process: ");
//        System.out.println("Enter Title: ");
//        String title = scanner.nextLine();
//        System.out.println("Enter Author: ");
//        String author = scanner.nextLine();
//        System.out.println("Enter Genre: ");
//        String genre = scanner.nextLine();
//        System.out.println("Enter Price: ");
//        double price = scanner.nextDouble();
//        System.out.println("Enter Quantity: ");
//        int quantity = scanner.nextInt();
//
//
//        Book book = new Book(title,author,genre,price,quantity);
//        BookService bs = new BookService();
////        BookController bc = new BookController(bs);
////        bc.CreateBook(book);
//
//        // delete book
////        System.out.println("Delete Book process: ");
////        System.out.println("Enter Title: ");
////        String title = scanner.nextLine();
////        System.out.println("Enter Author: ");
////        String author = scanner.nextLine();
////        System.out.println("Enter Genre: ");
////        String genre = scanner.nextLine();
////        System.out.println("Enter Price: ");
////        double price = scanner.nextDouble();
////        System.out.println("Enter Quantity: ");
////        int quantity = scanner.nextInt();
////
////        Book book = new Book(title,author,genre,price,quantity);
////        BookService bs = new BookService();
////        BookController bc = new BookController(bs);
////        bs.delete(book);
//
//
////        // uodate book
////        System.out.println("uodate Book process: ");
////        System.out.println("Enter Title: ");
////        String title = scanner.nextLine();
////        System.out.println("Enter Author: ");
////        String author = scanner.nextLine();
////        System.out.println("Enter Genre: ");
////        String genre = scanner.nextLine();
////        System.out.println("Enter Price: ");
////        double price = scanner.nextDouble();
////        System.out.println("Enter Quantity: ");
////        int quantity = scanner.nextInt();
////
////        Book book = new Book(title,author,genre,price,quantity);
////        BookService bs = new BookService();
////        BookController bc = new BookController(bs);
////        bs.update(book);
//
//
//
//        // to search for books by title working okay
////        BookService bc = new BookService();
////        BookController bookController = new BookController(bc);
////        String title = "utobia";
////
////        try {
////            Book book = bookController.getBookByTitle(title);
////
////            if (book == null) {
////                System.out.println("No books found by title: " + title);
////            } else {
////                System.out.println("Books with " + title + " Title :");
////                System.out.println("- Author: " + book.getAuthor());
////                System.out.println("- Genre: " + book.getGenre());
////                System.out.println("- Price: " + book.getPrice());
////                System.out.println("- Quantity: " + book.getQuantity());
////
////            }
////        } catch (Exception e) {
////
////            System.err.println(" error Here in book title view: " + e.getMessage());
////        }
//
//
//
//
//
//        // to search for books by author working okay
//
////        BookService bc = new BookService();
////        BookController bookController = new BookController(bc);
////        String authorName = "ahmedkhaledtawfik";
////
////        try {
////            List<Book> books = bookController.getBooksByAuthor(authorName);
////
////            if (books.isEmpty()) {
////                System.out.println("No books found by author: " + authorName);
////            } else {
////                System.out.println("Books by " + authorName + ":");
////                for (Book book : books) {
////                    System.out.println("- " + book.getTitle());
////                }
////            }
////        } catch (Exception e) {
////
////            System.err.println(" error Here in books author view: " + e.getMessage());
////        }
//
//
//        // to search for books by genre working okay
//
////        BookService bc = new BookService();
////        BookController bookController = new BookController(bc);
////        String genre = "mathmatics";
////
////        try {
////            List<Book> books = bookController.getBooksByGenre(genre);
////
////            if (books.isEmpty()) {
////                System.out.println("No books found by genre: " + genre);
////            } else {
////                System.out.println("Books by " + genre + ":");
////                for (Book book : books) {
////                    System.out.println("- " + book.getTitle());
////                }
////            }
////        } catch (Exception e) {
////
////            System.err.println(" error Here in books genre view: " + e.getMessage());
////        }
//
//
//
//
////        switch(choice){
////            case 1:
////                System.out.println("Please Enter Author Name: ");
////                String author = scanner.nextLine();
////                bookController.getBooksByAuthor(author);
////                break;
////            case 2:
////                System.out.println("Please Enter Title Name: ");
////                String title = scanner.nextLine();
////                bookController.getBookByTitle(title);
////                break;
////            case 3:
////                System.out.println("Please Enter Genre Name: ");
////                String genre = scanner.nextLine();
////                bookController.getBooksByAuthor(genre);
////                break;
////
////        }
//
//
//
//
////        try (Connection con = DriverManager.getConnection(url);
////             Statement st = con.createStatement();
////             ResultSet rs = st.executeQuery(sql)) {
////
////            while (rs.next()) {
////                String name = rs.getString("username");
////                System.out.println(name);
////            }
////
////        } catch (SQLException e) {
////            System.out.println("Failed to connect to database " + e.getMessage());
////            e.printStackTrace();
////        }
//    }
//}
