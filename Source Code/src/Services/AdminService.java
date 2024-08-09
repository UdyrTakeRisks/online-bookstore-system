package Services;

import Models.Admin;
import Models.Book;
import Models.Request;
import Persistence.AdminDAO;
import Persistence.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminService implements AdminDAO {
    private List<Admin> admins;

    public AdminService(){
        this.admins = new ArrayList<>();
    }
    @Override
    public Admin getById(int id) {
        for (Admin admin : admins) {
            if (admin.getId() == id) {
                return admin;
            }
        }
        return null;
    }
    @Override
    public List<Admin> getAll() {
        return admins;
    }

    @Override
    public List<Request> getRequests() {
        List<Request> requests = new ArrayList<>();
        String sql = "SELECT * FROM Requests";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Request request = new Request();
                    request.setId(resultSet.getInt("request_id"));
//                    request.setBorrower_id(resultSet.getInt("borrower_id"));
//                    request.setLender_id(resultSet.getInt("lender_id"));
//                    request.setBook_id(resultSet.getInt("book_id"));
                    request.setRequest_status(resultSet.getString("request_status"));
                    request.setBorrower_name(resultSet.getString("borrower_name"));
                    request.setLender_name(resultSet.getString("lender_name"));
                    request.setBook_name(resultSet.getString("book_name"));
                    requests.add(request);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting Requests: " + e.getMessage());
        }
        return requests;
    }
    @Override
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Books";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setId(resultSet.getInt("book_id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setGenre(resultSet.getString("genre"));
                    book.setPrice(resultSet.getDouble("price"));
                    book.setQuantity(resultSet.getInt("quantity"));
                    book.setLender_id(resultSet.getInt("lender_id"));
                    book.setBorrower_id(resultSet.getInt("borrower_id"));
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting books: " + e.getMessage());
        }
        return books;
    }
    @Override
    public List<Book> getAvailableBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Books WHERE borrower_id IS NULL";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setId(resultSet.getInt("book_id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setGenre(resultSet.getString("genre"));
                    book.setPrice(resultSet.getDouble("price"));
                    book.setQuantity(resultSet.getInt("quantity"));
                    book.setLender_id(resultSet.getInt("lender_id"));
                    book.setBorrower_id(resultSet.getInt("borrower_id"));
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting Available Books: " + e.getMessage());
        }
        return books;
    }

    @Override
    public void create(Admin admin) {
        admins.add(admin);
        System.out.println("Admin added successfully");
    }

    @Override
    public void update(Admin updatedAdmin) {
        for (int i = 0; i < admins.size(); i++) {
            Admin admin = admins.get(i);
            if (admin.getId() == updatedAdmin.getId()) {

                admin.setUsername(updatedAdmin.getUsername());
                admin.setPassword(updatedAdmin.getPassword());
                System.out.println("Admin updated successfully");
                return;
            }
        }
        System.out.println("Admin not found");
    }

    @Override
    public void delete(Admin admin) {
        admins.remove(admin);
        System.out.println("Admin deleted successfully");
    }




}
