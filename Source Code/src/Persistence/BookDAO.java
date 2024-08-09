package Persistence;

import Models.Book;

import java.util.List;

public interface BookDAO {

    Book getById(int id);
    String getByTitle(int lender_id, String title);
    Book getByTitle(String title);

    List<Book> getByAuthor(String author);
    List<Book> getByGenre(String genre);
    List<Book> getAll();

    String create(Book book);

    void update(Book book);
    void updateBorrowID(int borrow_id, String bookTitle);
    int getIdByTitle(String bookTitle);
    String getTitleById(int book_id);
    String delete(Book book);
}
