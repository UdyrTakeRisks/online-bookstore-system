package Controllers;

import Models.Book;
import Models.Request;
import Services.AdminService;
import Services.BookService;

import java.util.List;

public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    public List<Request> getAllRequests(){
        return adminService.getRequests();
    }
    public List<Book> getAllBooks(){
        return adminService.getBooks();
    }
    public List<Book> getAvailableBooks(){
        return adminService.getAvailableBooks();
    }

}
