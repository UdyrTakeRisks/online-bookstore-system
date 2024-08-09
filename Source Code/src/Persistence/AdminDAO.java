package Persistence;

import Models.Admin;
import Models.Request;
import Models.Book;

import java.util.List;

public interface AdminDAO {
    Admin getById(int id);

    List<Admin> getAll();
    List<Request> getRequests();
    List<Book> getBooks();
    List<Book> getAvailableBooks();
    void create(Admin admin);

    void update(Admin admin);

    void delete(Admin admin);
}
