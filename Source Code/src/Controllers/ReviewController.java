package Controllers;

import Models.Review;
import Services.ReviewService;

import java.util.List;

public class ReviewController {
    private ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    public Review getReviewById(int review_id) {
        return reviewService.getById(review_id);
    }
    public List<Review> getBookById(int book_id) {
        return reviewService.getByBookId(book_id);
    }
    public String createReview(Review review){
        return reviewService.create(review);
    }
//    public void updateReview(Review review){
//        reviewService.update(review);
//    }
    public void deleteReview(int review_id){
        reviewService.delete(review_id);
    }
    public double calculateRating(int book_id){
        return reviewService.calculateRating(book_id);
    }
    public List<Review> viewCurrentlyReviewedBooks(){
        return reviewService.displayCurrentReviewedBooks();
    }

}
