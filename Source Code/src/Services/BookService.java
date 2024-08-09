package Services;

import Models.Book;
import Models.User;
import Persistence.BookDAO;
import Persistence.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService implements BookDAO {
    private List<Book> books;

    public BookService(){
        this.books = new ArrayList<>();
    }

    @Override
    public Book getById(int id) {
         for(Book book:books){
             if(book.getId() == id){
                 return book;
             }
         }
         return null;
    }
    @Override
    public String getByTitle(int lender_id, String title) {
        String bookTitle = "";
        String sql = "Select title from Books where lender_id = ? and title = ?";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,lender_id);
            statement.setString(2,title);
            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()) { // resulset.next function to get a specific row
                    bookTitle = resultSet.getString("title");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting books by Genre: " + e.getMessage());
        }
        return bookTitle;
    }
    @Override
    public Book getByTitle(String title) {
        Book book = new Book();
        String sql = "Select * from Books where title = ?";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,title);
            try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) { // resulset.next function used to iterate for each row in database

                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setGenre(resultSet.getString("genre"));
                    book.setPrice(resultSet.getDouble("price"));
                    book.setQuantity(resultSet.getInt("quantity"));

                }
            }

        } catch (SQLException e) {
            System.err.println("Error getting books by Genre: " + e.getMessage());

        }
        return book;
    }

    @Override
    public List<Book> getByAuthor(String author) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM Books WHERE author = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, author);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setGenre(resultSet.getString("genre"));
                    book.setPrice(resultSet.getDouble("price"));
                    book.setQuantity(resultSet.getInt("quantity"));
                    books.add(book);
                }
            }
        } catch (SQLException e) {

            System.err.println("Error getting books by author: " + e.getMessage());
        }

        return books;
    }

    @Override
    public List<Book> getByGenre(String genre) {
        List<Book> books = new ArrayList<>();
        String sql = "Select * from Books where genre = ?";
        try(Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1,genre);
                try(ResultSet resultSet = statement.executeQuery()){
                while(resultSet.next()) { // resulset.next function used to iterate for each row in database
                    Book book = new Book();
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setGenre(resultSet.getString("genre"));


                    book.setPrice(resultSet.getDouble("price"));
                    book.setQuantity(resultSet.getInt("quantity"));
                    books.add(book);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error getting books by Genre: " + e.getMessage());

        }
        return books;
    }

    public int getIdByTitle(String bookTitle){
        String sql = "SELECT book_id FROM Books WHERE title = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bookTitle);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("book_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public String getTitleById(int book_id){
        String sql = "SELECT title FROM Books WHERE book_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, book_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("title");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Book title is not found";
    }

    @Override
    public List<Book> getAll() {
        return books;
    }

    @Override
    public String create(Book book) {
        String sql = "INSERT INTO Books (title,author,genre,price,quantity, lender_id) VALUES(?,?,?,?,?,?)";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql); ) {
            String userInput = book.getGenre();
            String userInputOrNull = (userInput.isEmpty()) ? null : userInput;
            statement.setString(1,book.getTitle());
            statement.setString(2,book.getAuthor());
//            statement.setString(3,book.getGenre());
            statement.setString(3,userInputOrNull);
            statement.setDouble(4,book.getPrice());
            statement.setInt(5,book.getQuantity());
            statement.setInt(6,book.getLender_id());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return ("Connection failed to database to create book");
        }
        return ("Book created successfully!");
    }

    @Override
    public void update(Book updateBook) {
        String sql = "UPDATE Books SET title = ?, author = ?, genre = ? , price = ? , quantity = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, updateBook.getTitle());
            statement.setString(2, updateBook.getAuthor());
            statement.setString(3, updateBook.getGenre());
            statement.setInt(4, updateBook.getId());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book updated successfully");
            } else {
                System.out.println("Book not found or not updated");
            }

        } catch (SQLException e) {
            System.err.println("Error updating book: " + e.getMessage());
        }
    }
    @Override
    public void updateBorrowID(int borrow_id, String bookTitle) {
        String sql = "UPDATE Books SET borrower_id = ? WHERE title = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, borrow_id);
            statement.setString(2, bookTitle);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book updated successfully");
            } else {
                System.out.println("Book not found or not updated");
            }
        } catch (SQLException e) {
            System.err.println("Error updating book: " + e.getMessage());
        }
    }
    @Override
    public String delete(Book deletedBook) {
        String sql = "DELETE FROM Books WHERE title = ?";
        String MSG = "";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, deletedBook.getTitle());

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                MSG = "Book deleted successfully";
            } else {
                MSG = "Book not found or not deleted";
            }

        } catch (SQLException e) {
            System.err.println("Error deleting book: " + e.getMessage());
        }
        return MSG;
    }




//    private List<Book> extractBookFromResultSet(ResultSet resultSet) throws SQLException {
//        int id = resultSet.getInt("id");
//        String title = resultSet.getString("title");
//        String author = resultSet.getString("author");
//        String genre = resultSet.getString("genre");
//        double price = resultSet.getDouble("price");
//        int quantity = resultSet.getInt("quantity");
//        Book book = new Book(id,title,author,genre,price,quantity);
//    }
}
