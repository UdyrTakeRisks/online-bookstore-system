package Controllers;

import Models.Book;
import Services.BookService;

import java.util.List;

public class BookController {
    //    private final BookService bookService;
    public BookService bookService = new BookService();

//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }

    public BookController() {
    }

    ;

    public Book getBookById(int bookId) {
        return bookService.getById(bookId);
    }

    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    public List<Book> getBooksByGenre(String genre) {
        return bookService.getByGenre(genre);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookService.getByAuthor(author);
    }

    //    public Book getBookByTitle(String title){return bookService.getByTitle(title);}
    public Book getBookByTitle(String title) {
        return bookService.getByTitle(title);
    }

    public String getBookByTitle(int lender_id, String title) {
        return bookService.getByTitle(lender_id, title);
    }
    public int getIdByTitle(String bookTitle){
        return bookService.getIdByTitle(bookTitle);
    }
    public String getTitleById(int book_id){
        return bookService.getTitleById(book_id);
    }
    public String CreateBook(Book book) {
        return bookService.create(book);
    }

    public String DeleteBook(Book deletedBook) {
        return bookService.delete(deletedBook);
    }

    public void UpdateBook(Book UpdatedBook) {
        bookService.update(UpdatedBook);
    }
    public void updateBorrowID(int borrow_id, String bookTitle) {
        bookService.updateBorrowID(borrow_id, bookTitle);
    }
}
