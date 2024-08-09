package Services;

import Models.Review;
import Persistence.DBConnection;
import Persistence.ReviewDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewService implements ReviewDAO  {

    @Override
    public Review getById(int review_id) {
        try (Connection connection = DBConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Reviews WHERE review_id = ?");
            statement.setInt(1, review_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Review(
                        resultSet.getInt("user_id"),
                        resultSet.getInt("book_id"),
                        resultSet.getString("book_name"),
                        resultSet.getString("review_msg"),
                        resultSet.getDouble("rating"),
                        resultSet.getString("reviewer_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Review not found
    }
    @Override
    public List<Review> getByBookId(int book_id) {
        List<Review> reviews = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Reviews WHERE book_id = ?");
            statement.setInt(1, book_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reviews.add(new Review(
                        resultSet.getInt("user_id"),
                        resultSet.getInt("book_id"),
                        resultSet.getString("book_name"),
                        resultSet.getString("review_msg"),
                        resultSet.getDouble("rating"),
                        resultSet.getString("reviewer_name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public String create(Review review) {
        try (Connection connection = DBConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Reviews (user_id, book_id, book_name, review_msg, rating, reviewer_name) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setInt(1, review.getReviewer());
            statement.setInt(2, review.getBookId());
            statement.setString(3, review.getBookTitle());
            statement.setString(4, review.getReviewText());
            if(review.getRating() < 0 || review.getRating() > 5)
                return ("Invalid Rating, Please enter rate between 0 - 5");
            statement.setDouble(5, review.getRating());
            statement.setString(6, review.getUsername());
            statement.executeUpdate();
        } catch (SQLException e){
            return ("SQL Problem: " + e.getMessage());
        }
        return ("Book Review has been done Successfully");
    }

//    @Override
//    public void update(Review review) {
//        try (Connection connection = DBConnection.getConnection()){
//            PreparedStatement statement = connection.prepareStatement("UPDATE Reviews SET userId = ?, bookId = ?, reviewText = ?, rating = ? WHERE review_id = ?");
//            statement.setInt(1, review.getReviewer());
//            statement.setInt(2, review.getBookId());
//            statement.setString(3, review.getReviewText());
//            statement.setDouble(4, review.getRating());
////            statement.setInt(5, review.getReviewId());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public void delete(int review_id) {
        try (Connection connection = DBConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Reviews WHERE review_id = ?");
            statement.setInt(1, review_id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double calculateRating(int book_id) {
        double averageRating = 0;
        try (Connection connection = DBConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT AVG(rating) AS avg_rating FROM Reviews WHERE book_id = ?");
            statement.setInt(1, book_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                averageRating = resultSet.getDouble("avg_rating");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return averageRating;
    }
    public List<Review> displayCurrentReviewedBooks(){
        List<Review> reviews = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT r1.review_id, r1.user_id, r1.book_id, r1.rating, r1.review_msg, r1.book_name, r1.reviewer_name\n" +
                                                                            "FROM Reviews r1\n" +
                                                                            "JOIN (\n" +
                                                                            "    SELECT MAX(review_id) AS max_review_id, book_id\n" +
                                                                            "    FROM Reviews\n" +
                                                                             "    GROUP BY book_id\n" +
                                                                             ") r2 ON r1.review_id = r2.max_review_id\n" +
                                                                             "ORDER BY r1.book_id;\n");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                reviews.add(new Review(
                        resultSet.getInt("user_id"),
                        resultSet.getInt("book_id"),
                        resultSet.getString("book_name"),
                        resultSet.getString("review_msg"),
                        resultSet.getDouble("rating"),
                        resultSet.getString("reviewer_name")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

}
