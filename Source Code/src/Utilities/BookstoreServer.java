package Utilities;

import Controllers.*;
import Models.Book;
import Models.Request;
import Models.Review;
import Models.User;
import Services.AdminService;
import Services.BookService;
import Services.RequestService;
import Services.ReviewService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static Utilities.MultiThreadedBookstore.*;

public class BookstoreServer implements Runnable {

    @Override
    public void run() {
        // run your logic here
        try {
//            BufferedReader readFromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataInputStream readFromClient = new DataInputStream(socket.getInputStream()); //
            DataOutputStream writeToClient = new DataOutputStream(socket.getOutputStream());
            UserController userController = new UserController();
            BookController bc = new BookController();
            RequestController rc = new RequestController(new RequestService());
            AdminController ac = new AdminController(new AdminService());
            ReviewController reviewController = new ReviewController(new ReviewService());
            User user = new User();
            Book book = new Book();
            String loginResponse = "";
//            Scanner scanner = new Scanner(System.in);
//            String line = readFromClient.readLine();
            String line = "";
            while (!line.equals("stop")) {
                line = readFromClient.readUTF();
                System.out.println("Line: " + line);
                if (line.equals("Add Book")) {
                    System.out.println("after if");
                    String title = readFromClient.readUTF();
                    String author = readFromClient.readUTF();
                    String genre = readFromClient.readUTF();
                    double price = Double.parseDouble(readFromClient.readUTF());
                    int quantity = Integer.parseInt(readFromClient.readUTF());
                    int lender_id = 0;
                    if (loginResponse.equals("Login successful.")) {
                        lender_id = userController.getUserId(user.getUsername());
                        System.out.println(title + author + genre + price + quantity + lender_id);
                        book = new Book(title, author, genre, price, quantity, lender_id);
                        String res = bc.CreateBook(book);
                        writeToClient.writeUTF(res);
                    }else{
                        System.out.println("Please login first to add a book");
                    }

                } else if (line.equals("Remove Book")) {
                    System.out.println("after if");
                    String title = readFromClient.readUTF();
                    book = new Book(title);
                    String res = bc.DeleteBook(book);
                    writeToClient.writeUTF(res);
                } else if (line.equals("Login")) {
                    System.out.println("Iam entered login in scope");
                    String username = readFromClient.readUTF();
                    String password = readFromClient.readUTF();
                    System.out.println("User Name: " + username + " Password: " + password);
                    user = new User(username, password);
                    loginResponse = userController.loginUser(user);
                    writeToClient.writeUTF(loginResponse);
                } else if (line.equals("Sign Up")) {
                    System.out.println("Iam entered Sign Up scope");
                    String name = readFromClient.readUTF();
                    String username = readFromClient.readUTF();
                    String password = readFromClient.readUTF();
                    System.out.println("Name: " + name + " User Name: " + username + " Password: " + password);
                    user = new User(name, username, password);
                    String response = userController.registerUser(user);
                    writeToClient.writeUTF(response);
                } else if (line.equals("Search Books")) {
//                    writeToClient.writeUTF("Search Books");
                    String subchoice = readFromClient.readUTF();
                    System.out.println("subchoice: "+subchoice);
                    switch (subchoice) {
                        case "1":
                            System.out.println("Title: ");
                            String title = readFromClient.readUTF();
                            book = bc.getBookByTitle(title);
                            if(book.toString().contains("null"))
                                writeToClient.writeUTF("Book Not Found");
                            else
                                writeToClient.writeUTF(book.toString());
                            break;
                        case "2":
                            System.out.println("Author: ");
                            String author = readFromClient.readUTF();
                            List<Book> books = bc.getBooksByAuthor(author);
                            if(books.toString().matches("\\[\\]"))
                                writeToClient.writeUTF("Book Not Found");
                            else
                                writeToClient.writeUTF(books.toString());
                            break;
                        case "3":
                            System.out.println("Genre: ");
                            String genre = readFromClient.readUTF();
                            List<Book> genreBooks = bc.getBooksByGenre(genre);
                            if(genreBooks.toString().matches("\\[\\]"))
                                writeToClient.writeUTF("Book Not Found");
                            else
                                writeToClient.writeUTF(genreBooks.toString());
                            break;
                        default:
                            System.out.println("Invalid input please enter valid input !!");
                            writeToClient.writeUTF("Invalid input please enter valid input !!");
                    }

                } else if (line.equals("Send Borrow Request")) {
//                    DataInputStream read = new DataInputStream(socket2.getInputStream()); //
//                    DataOutputStream write = new DataOutputStream(socket2.getOutputStream());
                    
                } else if (line.equals("Show Request History")) {
                    System.out.println("Requests: ");
                    String userName = readFromClient.readUTF();
                    int borrow_id = userController.getUserId(userName);
                    List<Request> requests = rc.getRequestsById(borrow_id);
                    if(requests.toString().matches("\\[\\]"))
                        writeToClient.writeUTF("Request Not Found");
                    else
                        writeToClient.writeUTF(requests.toString());

                } else if (line.equals("Show Library Overall Statistics")) {
                    System.out.println("Iam entered Admin login in scope");
                    String username = readFromClient.readUTF();
                    String password = readFromClient.readUTF();
                    System.out.println("User Name: " + username + " Password: " + password);
                    if(username.equals("admin") && password.equals("admin123")){
                        writeToClient.writeUTF("Admin has logged in successfully");
                        // write libraryStat
                        // get library stat logic - database
                        List<Request> allRequests = ac.getAllRequests();
                        List<Book> getAllBooks = ac.getAllBooks();
                        List<Book> getAvailableBooks = ac.getAvailableBooks();
                        if(allRequests.toString().matches("\\[\\]")){
                            writeToClient.writeUTF("No Requests are Found");
                        }else{
                            writeToClient.writeUTF(allRequests.toString());
                        }
                        if (getAllBooks.toString().matches("\\[\\]")) {
                            writeToClient.writeUTF("No Books are Found");
                        }else{
                            writeToClient.writeUTF(getAllBooks.toString());
                        }
                        if (getAvailableBooks.toString().matches("\\[\\]")) {
                            writeToClient.writeUTF("No Available Books Found");
                        }else{
                            writeToClient.writeUTF(getAvailableBooks.toString());
                        }
                    }else{
                        writeToClient.writeUTF("401 unauthorized access");
                    }
                } // 9 - Review Book
                else if (line.equals("Review Book")) {
                    String username = readFromClient.readUTF();
                    String bookTitle = readFromClient.readUTF();
                    double rating = Double.parseDouble(readFromClient.readUTF());
                    String review_msg = readFromClient.readUTF();
                    int user_id = userController.getUserId(username);
                    int book_id = bc.getIdByTitle(bookTitle);
                    String response = reviewController.createReview(new Review(user_id, book_id, bookTitle, review_msg, rating, username));
                    writeToClient.writeUTF(response);
                } // 10 - Calculate Book Accumulated Rating
                else if(line.equals("Calculate Book Accumulated Rating")){
                    String bookTitle = readFromClient.readUTF();
                    int book_id = bc.getIdByTitle(bookTitle);
                    String bookTitleDB = bc.getTitleById(book_id);
                    double accumulatedRating = reviewController.calculateRating(book_id);
                    if(bookTitle.equals(bookTitleDB))
                        writeToClient.writeUTF(String.valueOf(accumulatedRating));
                    else
                        writeToClient.writeUTF("Book title is not found to rate");
                } // 11 - Display Books Recommendation
                else if (line.equals("Display Books Recommendation")) {
                    System.out.println("Display Currently Reviewed Books: ");
                    List<Review> newlyReviewedBooks = reviewController.viewCurrentlyReviewedBooks();
                    if(newlyReviewedBooks.toString().matches("\\[\\]"))
                        writeToClient.writeUTF("Currently Reviews Not Found");
                    else
                        writeToClient.writeUTF(newlyReviewedBooks.toString());
                }
            }

            readFromClient.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}