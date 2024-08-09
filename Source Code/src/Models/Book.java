package Models;

import java.util.List;

public class Book {
    private int id;
    private String title;
    private String author;

    private String genre;

    private double price;

    private int quantity;

    private int lender_id;

    private int borrower_id;
    private List<User> borrowers;
    private List<Request> requests;

    private Admin admin;


    public Book() {
    }


    public Book(String title, String author, String genre, double price, int quantity, int lender_id) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
        this.lender_id = lender_id;
    }

    public Book(String title) {
        this.title = title;
    }


    //region setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setClients(List<User> _borrowers) {
        borrowers = _borrowers;
    }

    public void setRequests(List<Request> _requests) {
        requests = _requests;
    }

    public void setAdmin(Admin _admin) {
        admin = _admin;
    }
    //endregion

    //region getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<User> getBorrowers() {
        return borrowers;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public Admin getAdmin() {
        return admin;
    }
    //endregion

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        sb.append("Book ID: ").append(id).append("\n");
        sb.append("Title: ").append(title).append("\n");
        sb.append("Author: ").append(author).append("\n");
        sb.append("Genre: ").append(genre).append("\n");
        sb.append("Price: ").append(price).append("\n");
        sb.append("Quantity: ").append(quantity).append("\n");
        if (borrowers != null && !borrowers.isEmpty()) {
            sb.append("Borrowers: ").append(borrowers).append("\n");
        }
        if (requests != null && !requests.isEmpty()) {
            sb.append("Requests: ").append(requests).append("\n");
        }
        if (admin != null) {
            sb.append("Admin: ").append(admin).append("\n");
        }
        return sb.toString();
    }


    public int getLender_id() {
        return lender_id;
    }

    public void setLender_id(int lender_id) {
        this.lender_id = lender_id;
    }

    public int getBorrower_id() {
        return borrower_id;
    }

    public void setBorrower_id(int borrower_id) {
        this.borrower_id = borrower_id;
    }
}
