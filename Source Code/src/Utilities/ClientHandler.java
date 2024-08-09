package Utilities;

import Controllers.BookController;
import Controllers.RequestController;
import Controllers.UserController;
import Models.Book;
import Models.Request;
import Models.User;
import Services.RequestService;

import java.io.*;
import java.net.Socket;
import java.util.*;

public class ClientHandler implements Runnable { // handle borrowing requests
    public Socket socketHandler;

    public ClientHandler(Socket socket) {
        this.socketHandler = socket;
    }

    static List<String[]> allElem = new ArrayList<>(); // size is always 2 (sender and receiver)

    @Override
    public void run() {
        try {
            // read from specific client
            DataInputStream in = new DataInputStream(socketHandler.getInputStream()); //
            String borrowRequest = "";
            while (!borrowRequest.equals("close")) { //always send request
                borrowRequest = in.readUTF();
                System.out.println("debug: " + borrowRequest);
                MultiThreadedBookstore.submitBorrowRequest(this, borrowRequest);
                validateBorrowRequest(borrowRequest);
            }
//            readFromServer.close();
//            writeToServer.close();
//            socket.close();
        } catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
        }
    }

    //process the request here
    public void validateBorrowRequest(String borrowRequest) throws IllegalArgumentException {
        String[] elements = borrowRequest.split(":");
        if (elements.length != 2)
            throw new IllegalArgumentException("Parsing/Validation Error, please ensure the above format");

        if (borrowRequest.matches("[a-zA-Z0-9]+:Accept")) { // lender choice // 2
            System.out.println("Accepted Request");
            String[] receiveRequest = borrowRequest.split(":");
            allElem.add(receiveRequest);
            // [ahmed2024, cs]
            // [ahmed123, Reject]
            String borrowerUser = allElem.get(0)[0];
            String bookTitle = allElem.get(0)[1];
            String lenderUser = allElem.get(1)[0];
            String requestStatus = allElem.get(1)[1];
//            for debug
//            System.out.println("Borrower User: " + borrowerUser);
//            System.out.println("Book Title: " + bookTitle);
//            System.out.println("Lender User: " + lenderUser);
//            System.out.println("Status: " + requestStatus);

            // should handle if the lender_id is related to the same book title
            // also handle the quantity (minor)
            // request logic
            UserController uc = new UserController();
            BookController bc = new BookController();
            RequestController rc = new RequestController(new RequestService());

            int lender_id = uc.getUserId(lenderUser);
            String bookTitleFromDb = bc.getBookByTitle(lender_id, bookTitle);
            if (bookTitleFromDb.equals(bookTitle)) {
                // accept -> add borrower_id to the book, add the request info
                int borrower_id = uc.getUserId(borrowerUser);
                bc.updateBorrowID(borrower_id, bookTitle); //
                int book_id = bc.getIdByTitle(bookTitle);
                Request request = new Request(borrower_id, lender_id, book_id, "accepted", borrowerUser, lenderUser, bookTitle);
                rc.addRequest(request);
            } else {
                //reject because the lender doesn't have the book
//                Request request = new Request(borrower_id, lender_id, book_id, "rejected");
//                rc.addRequest(request);
            }

        } else if (borrowRequest.matches("[a-zA-Z0-9]+:Reject")) {  // lender choice
            System.out.println("Rejected Request");
//            Request request = new Request(borrower_id, lender_id, book_id, "rejected");
//                rc.addRequest(request);
        } else if (borrowRequest.matches("[a-zA-Z0-9]+:[a-zA-Z\\s]+")) { // 1
            System.out.println("request title: " + borrowRequest);
            String[] sendRequest = borrowRequest.split(":");
            allElem.add(sendRequest);
        } else {
            System.out.println("Invalid Format please request again");
        }
    }

//    public String[] validateSendRequest(String borrowingRequest) {
//        if (borrowingRequest.matches("[a-zA-Z0-9]+:[a-zA-Z]+")) { // for borrowers request [Book Title]
//            System.out.println("request title: " + borrowingRequest);
//            return borrowingRequest.split(":");
//            //if boolean is true add borrower to the lender book
//
////            Book book = new Book();
////            BookController bc = new BookController();
////            bc.UpdateBook(); // Add borrower_id by getting the user_id from
//
//        } else {
//            return new String[0];
//        }
//    }

}
