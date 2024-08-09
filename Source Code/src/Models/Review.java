package Models;

public class Review {
    private User reviewer; //
    private Book book; //
    private double rating;
    private String review_msg;
    private String bookTitle;
    private String username;
    public Review(int userId, int bookId, String bookTitle, String reviewText, double rating, String username) {
        this.reviewer = new User();
        this.reviewer.setId(userId);
        this.book = new Book();
        this.book.setId(bookId);
        this.bookTitle = bookTitle;
        this.review_msg = reviewText;
        this.rating = rating;
        this.username = username;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setReviewText(String reviewText) {
        this.review_msg = reviewText;
    }

    public int getReviewer() {
        return reviewer.getId();
    }

    public int getBookId() {
        return book.getId();
    }

    public double getRating() {
        return rating;
    }

    public String getReviewText() {
        return review_msg;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        sb.append("Request ID: ").append(id).append("\n");
        sb.append("Reviewer Name: ").append(username).append("\n");
        sb.append("Book Name: ").append(bookTitle).append("\n");
        sb.append("Rating: ").append(rating).append("\n");
        sb.append("Review Message: ").append(review_msg).append("\n");

        return sb.toString();
    }
}
