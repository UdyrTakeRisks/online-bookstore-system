package Utilities;

import Controllers.BookController;
import Controllers.UserController;
import Models.Book;
import Views.BookView;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class BookstoreClient {
    private static final String hostname = "127.0.0.1";
    private static final int serverPort = 5000;
    private static final int serverCommunicationPort = 5005;

    public static void main(String[] args) {
        try {
            System.out.println("Client Started...");
            Socket socket = new Socket(hostname, serverPort);
            Socket socket2 = new Socket(hostname, serverCommunicationPort);
            System.out.println("Client Connected..." + " on port: " + socket.getPort() + " and Communication port: " + socket2.getPort());

            // write "Add Book" to server to parse it
//            BufferedWriter writeToServer = new BufferedWriter(
//                    new OutputStreamWriter(socket.getOutputStream()));
            DataOutputStream writeToServer = new DataOutputStream(socket.getOutputStream());
//            BufferedReader readFromServer = new BufferedReader(
//                    new InputStreamReader(socket.getInputStream()));
            DataInputStream readFromServer = new DataInputStream(socket.getInputStream());
//            DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));

            int userChoice;
            Scanner scanner = new Scanner(System.in);
            do {
                BookView.displayMainMenu();
                System.out.print("Enter your choice: ");
                userChoice = scanner.nextInt();
                if (userChoice == 12)
                    System.exit(0); // is the best option to close app with threading
                if (userChoice == 1) {
//                    writeToServer.write("Add Book");
                    writeToServer.writeUTF("Add Book");
                    writeToServer.flush();
                    System.out.println("Create Book process: ");
                    System.out.println("Enter Title: ");
                    scanner.nextLine();
                    String title = scanner.nextLine();
                    writeToServer.writeUTF(title);
                    System.out.println("Enter Author: ");
                    String author = scanner.nextLine();
                    writeToServer.writeUTF(author);
                    System.out.println("Enter Genre: ");
                    String genre = scanner.nextLine();
                    writeToServer.writeUTF(genre);
                    System.out.println("Enter Price: ");
                    String price = scanner.nextLine();
                    writeToServer.writeUTF(price);
                    System.out.println("Enter Quantity: ");
                    String quantity = scanner.nextLine();
                    writeToServer.writeUTF(quantity);

                } else if (userChoice == 2) {
//                    writeToServer.write("Remove Book");
                    writeToServer.writeUTF("Remove Book");
                    writeToServer.flush();
                    System.out.println("Delete Book process: ");
                    System.out.println("Enter Title: ");
                    scanner.nextLine();
                    String title = scanner.nextLine();
                    writeToServer.writeUTF(title);

                } else if (userChoice == 3) {
                    writeToServer.writeUTF("Login");
                    writeToServer.flush();
                    scanner.nextLine();
                    System.out.println("User Name: ");
                    String username = scanner.nextLine();
                    writeToServer.writeUTF(username);
                    System.out.println("Password: ");
                    String password = scanner.nextLine();
                    writeToServer.writeUTF(password);

                } else if (userChoice == 4) {
                    writeToServer.writeUTF("Sign Up");
                    writeToServer.flush();
                    scanner.nextLine();
                    System.out.println("Name: ");
                    String name = scanner.nextLine();
                    writeToServer.writeUTF(name);
                    System.out.println("User Name: ");
                    String username = scanner.nextLine();
                    writeToServer.writeUTF(username);
                    System.out.println("Password: ");
                    String password = scanner.nextLine();
                    writeToServer.writeUTF(password);

                } else if (userChoice == 5) {
                    writeToServer.writeUTF("Search Books");
                    writeToServer.flush();
                    scanner.nextLine();
                    System.out.println("Choose Criteria Search: ");
                    System.out.println("1. Search By Title.");
                    System.out.println("2. Search By Author.");
                    System.out.println("3. Search By Genre.");
                    int subchoice = scanner.nextInt();
                    switch (subchoice) {
                        case 1:
                            writeToServer.writeUTF("1");
                            scanner.nextLine();
                            System.out.println("Title: ");
                            String title = scanner.nextLine();
                            writeToServer.writeUTF(title);
                            break;
                        case 2:
                            writeToServer.writeUTF("2");
                            scanner.nextLine();
                            System.out.println("Author: ");
                            String author = scanner.nextLine();
                            writeToServer.writeUTF(author);
                            break;
                        case 3:
                            writeToServer.writeUTF("3");
                            scanner.nextLine();
                            System.out.println("Genre: ");
                            String genre = scanner.nextLine();
                            writeToServer.writeUTF(genre);
                            break;
                        default:
                            System.out.println("Invalid input please enter valid input !!");
                    }
                } else if (userChoice == 6) {
                    // handling borrow request
                    writeToServer.writeUTF("Send Borrow Request");
                    writeToServer.flush();

                    Thread receiveThread = new Thread(() -> {
                        try {
                            String message = "";
                            DataInputStream NotifyMsg = new DataInputStream(socket2.getInputStream());
//                            while (!message.equals("end")) {
                            message = NotifyMsg.readUTF();
                            System.out.println("Notification " + message);
//                            }
                        } catch (SocketException s) {
                            // This exception is thrown when the server socket is closed
                            System.out.println("Server has closed the connection.");
                        } catch (Exception e) {
                            System.out.println("Error receiving message from server: " + e.getMessage());
                        }
                    });
                    receiveThread.start();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    DataOutputStream writeTo = new DataOutputStream(socket2.getOutputStream());
                    String message;
//                    do {
                    System.out.println("Send/Receive your Request:");
                    System.out.println("To send request, " +
                            "just type '<Username>:<BookTitle>'" +
                            " \nTo receive/verify request, " +
                            "just type '<Username>:Accept' or '<Username>:Reject' ");
                    message = reader.readLine();
                    writeTo.writeUTF(message);
                    System.out.println("----> Message sent to server");
//                    } while (!message.equals("end"));

                    receiveThread.join(); //
                    continue; // to Menu again after request communication
//                    socket.close();

                } else if (userChoice == 7) {
                    writeToServer.writeUTF("Show Request History");
                    writeToServer.flush();
                    scanner.nextLine();
                    System.out.println("To show your requests, Enter your User Name: ");
                    String username = scanner.nextLine();
                    writeToServer.writeUTF(username);
                } else if (userChoice == 8) {

                    writeToServer.writeUTF("Show Library Overall Statistics");
                    writeToServer.flush();
                    scanner.nextLine();
                    System.out.println("Admin Login: ");
                    System.out.println("User Name: ");
                    String username = scanner.nextLine();
                    writeToServer.writeUTF(username);
                    System.out.println("Password: ");
                    String password = scanner.nextLine();
                    writeToServer.writeUTF(password);

                    // read admin logged in or not
                    String response = readFromServer.readUTF();
                    if (response.equals("Admin has logged in successfully")) {
                        System.out.println("Showing Library Overall Statistics");
                        String allRequests = readFromServer.readUTF();
                        System.out.println("\nAll Requests: \n\n" + allRequests);
                        String allBooks = readFromServer.readUTF();
                        System.out.println("\nAll Books: \n\n" + allBooks);
                        String availableBooks = readFromServer.readUTF();
                        System.out.println("\nAvailable Books: \n\n" + availableBooks);
                    } else {
                        System.out.println("server response: " + response);
                    }
                    continue; // to skip iteration and go to menu
                } // 9 - Review Book
                else if (userChoice == 9) {
                    writeToServer.writeUTF("Review Book");
                    writeToServer.flush();
                    scanner.nextLine();
                    System.out.println("Review Process:");
                    System.out.println("User Name: ");
                    String username = scanner.nextLine();
                    writeToServer.writeUTF(username);
                    System.out.println("Book Title: ");
                    String bookTitle = scanner.nextLine();
                    writeToServer.writeUTF(bookTitle);
                    System.out.println("Rating: ");
                    String rating = scanner.nextLine();
                    writeToServer.writeUTF(rating);
                    System.out.println("Review Message: ");
                    String review_msg = scanner.nextLine();
                    writeToServer.writeUTF(review_msg);

                } // 10 - Calculate Book Accumulated Rating
                else if(userChoice == 10){
                    writeToServer.writeUTF("Calculate Book Accumulated Rating");
                    writeToServer.flush();
                    scanner.nextLine();
                    System.out.println("Calculating Accumulated Book Rating Process:");
                    System.out.println("Book Title: ");
                    String bookTitle = scanner.nextLine();
                    writeToServer.writeUTF(bookTitle);
                    String response = readFromServer.readUTF();
                    System.out.println("Server response: " + response);
                    continue; // go to menu
                } // 11 - Display Books Recommendation
                else if (userChoice == 11) {
                    writeToServer.writeUTF("Display Books Recommendation");
                    writeToServer.flush();
                    scanner.nextLine();
                    System.out.println("Display Currently Reviewed Books: ");
                }

                // after I finish my choice client reads the server response
                String response = readFromServer.readUTF();
                System.out.println("Server response: " + response);
//                writeToServer.flush();
            } while (userChoice != 12); // for example

            readFromServer.close();
            writeToServer.flush();
            writeToServer.close();
            socket.close();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }
}