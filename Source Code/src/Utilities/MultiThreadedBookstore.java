package Utilities;

import Controllers.BookController;
import Models.Book;
import Services.BookService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MultiThreadedBookstore { // Server
    private static List<ClientHandler> clients = new ArrayList<>();
    public static Socket socket;
    public static Socket socket2;
    public static ClientHandler clientHandler;
    public static Thread thread2;

    public static void main(String[] args) {
        try {
            int port = 5000, communicationPort = 5005;
            System.out.println("Multi-threaded Server started " + "on Port: " + port + " , CommunicationPort: " + communicationPort);
            ServerSocket serverSocket = new ServerSocket(port);
            ServerSocket serverSocket2 = new ServerSocket(communicationPort);
            while (true) {
                socket = serverSocket.accept(); // listen for clients
                socket2 = serverSocket2.accept();
                System.out.println("Client accepted..." + " on local port: " + socket.getPort() + " and Communication local port: " + socket2.getPort());
                BookstoreServer bookstoreServer = new BookstoreServer();
                // a new server thread for executing logic
                thread2 = new Thread(bookstoreServer);
                thread2.start();
                System.out.println("Server Thread2 no. " + thread2.getId());

                clientHandler = new ClientHandler(socket2);
                clients.add(clientHandler);
                // client handler thread for notifying a borrowing request
                Thread thread1 = new Thread(clientHandler);
                thread1.start();
                System.out.println("Client Handler Thread1 no. " + thread1.getId());
            }
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }
    public static void submitBorrowRequest(ClientHandler sender, String isSubmit) {
        for (ClientHandler client : clients) {
            if(client != sender){
                try {
                    System.out.println("debug submission");
                    DataOutputStream writer = new DataOutputStream(new BufferedOutputStream(client.socketHandler.getOutputStream()));
                    writer.writeUTF("request: " + isSubmit);
                    writer.flush();
                } catch (IOException e) {
                    System.out.println("Failed to notify client: " + e.getMessage());
                }
            }
        }
    }
}