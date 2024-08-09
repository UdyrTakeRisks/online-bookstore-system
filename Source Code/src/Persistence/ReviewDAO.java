package Persistence;

import Models.Review;
import Models.Review;

import java.util.List;

import java.util.List;

public interface ReviewDAO {


    Review getById(int id);
    List<Review> getByBookId(int bookId);
    String create(Review review);
//    void update(Review review);
    void delete(int id);
    double calculateRating(int bookId);
    List<Review> displayCurrentReviewedBooks();
}
