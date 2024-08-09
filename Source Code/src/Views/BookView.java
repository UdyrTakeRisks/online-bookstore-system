package Views;

import java.util.Scanner;

public class BookView {
    private final Scanner scanner;

    public BookView() {
        this.scanner = new Scanner(System.in);
    }
    public static void displayMainMenu() {
        System.out.println("------------------------------------------------------------");
        System.out.println("            Welcome To The Online BookStore :)          ");
        System.out.println("------------------------------------------------------------");
        System.out.println("                     1. Add Book                            ");
        System.out.println("                     2. Remove Book                         ");
        System.out.println("                     3. Login                               ");
        System.out.println("                     4. Sign Up                             ");
        System.out.println("                     5. Browse/Search Books                 ");
        System.out.println("                     6. Submit/Receive a Borrow Request     ");
        System.out.println("                     7. Show Request History                ");
        System.out.println("                     8. Show Library Overall Statistics     ");
        System.out.println("                     9. Review Book                         ");
        System.out.println("                     10. Calculate Book Accumulated Rating  ");
        System.out.println("                     11. Display Books Recommendation       ");
        System.out.println("                     12. Exit                               ");
    }

    public void displayBrowseSearchMenu() {
        System.out.println("------------------------------------------------------------");
        System.out.println("               Browse/Search Books Menu                ");
        System.out.println("------------------------------------------------------------");
        System.out.println("                1. Search by Title                         ");
        System.out.println("                2. Search by Author                        ");
        System.out.println("                3. Search by Genre                         ");
        System.out.println("                4. View All Books                          ");
        System.out.println("                5. Back to Main Menu                       ");
    }

    public void displayAddBookMenu() {
        System.out.println("------------------------------------------------------------");
        System.out.println("                     Add Book                            ");
        System.out.println("------------------------------------------------------------");
        System.out.println("Please enter the details of the book:");
        scanner.nextLine(); // Consume newline

        System.out.print("Id: ");
        int id = scanner.nextInt();

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Author: ");
        String author = scanner.nextLine();

        System.out.print("Genre: ");
        String genre = scanner.nextLine();

        System.out.print("Price: ");
        double price = scanner.nextDouble();

        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();

        // Call method to add the book to the inventory

    }

    public void displayRemoveBookMenu() {
        System.out.println("------------------------------------------------------------");
        System.out.println("                     Remove Book                         ");
        System.out.println("------------------------------------------------------------");
        System.out.println("Please enter the ID of the book you want to remove:");
        int bookId = scanner.nextInt();

        // Call method to remove the book from the inventory using the bookId
    }

}
