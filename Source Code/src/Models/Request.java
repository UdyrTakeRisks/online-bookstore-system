package Models;

import java.util.List;

public class Request {
    private int id;
    private int borrower_id;
    private int lender_id;
    private int book_id;
    private String request_status;
    private String borrower_name;
    private String lender_name;
    private String book_name;

    public Request() {

    }

    public Request(int borrower_id, int lender_id, int book_id, String request_status, String borrower_name, String lender_name, String book_name) {
        this.borrower_id = borrower_id;
        this.lender_id = lender_id;
        this.book_id = book_id;
        this.request_status = request_status;
        this.borrower_name = borrower_name;
        this.lender_name = lender_name;
        this.book_name = book_name;
    }

    //region setters
    public void setId(int id) {
        this.id = id;
    }


    //region getters
    public int getId() {
        return id;
    }

    public int getBorrower_id() {
        return borrower_id;
    }

    public void setBorrower_id(int borrower_id) {
        this.borrower_id = borrower_id;
    }

    public int getLender_id() {
        return lender_id;
    }

    public void setLender_id(int lender_id) {
        this.lender_id = lender_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getRequest_status() {
        return request_status;
    }

    public void setRequest_status(String request_status) {
        this.request_status = request_status;
    }
    //endregion

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getLender_name() {
        return lender_name;
    }

    public void setLender_name(String lender_name) {
        this.lender_name = lender_name;
    }

    public String getBorrower_name() {
        return borrower_name;
    }

    public void setBorrower_name(String borrower_name) {
        this.borrower_name = borrower_name;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        sb.append("Request ID: ").append(id).append("\n");
        sb.append("Borrower Name: ").append(borrower_name).append("\n");
        sb.append("Lender Name: ").append(lender_name).append("\n");
        sb.append("Book Name: ").append(book_name).append("\n");
        sb.append("Request Status: ").append(request_status).append("\n");

        return sb.toString();
    }
}
