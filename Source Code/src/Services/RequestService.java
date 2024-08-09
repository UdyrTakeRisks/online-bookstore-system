package Services;

import Models.Book;
import Models.Request;
import Models.Status;
import Models.User;
import Persistence.DBConnection;
import Persistence.RequestDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RequestService implements RequestDAO {
    private List<Request> requests;

    public RequestService(){
        this.requests = new ArrayList<>();
    }
    @Override
    public Request getById(int id) {
        for(Request request: requests){
            if(request.getId() == id){
                return request;
            }
        }
        return null;
    }

    @Override
    public String addRequest(Request request) {
        String sql = "INSERT INTO Requests (borrower_id, lender_id, book_id, request_status, borrower_name, lender_name, book_name) VALUES(?,?,?,?,?,?,?)";
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql); ) {
            statement.setInt(1, request.getBorrower_id());
            statement.setInt(2, request.getLender_id());
            statement.setInt(3, request.getBook_id());
            statement.setString(4, request.getRequest_status());
            statement.setString(5, request.getBorrower_name());
            statement.setString(6, request.getLender_name());
            statement.setString(7, request.getBook_name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return ("Connection failed to database to create Requests");
        }
        return ("Request created successfully!");
    }
    @Override
    public List<Request> getRequestsByBorrowId(int borrow_id){
        List<Request> requests = new ArrayList<>();
        String sql = "SELECT * FROM Requests WHERE borrower_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, borrow_id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Request request = new Request();
                    request.setBorrower_id(resultSet.getInt("borrower_id"));
                    request.setLender_id(resultSet.getInt("lender_id"));
                    request.setBook_id(resultSet.getInt("book_id"));
                    request.setRequest_status(resultSet.getString("request_status"));
                    request.setBorrower_name(resultSet.getString("borrower_name"));
                    request.setLender_name(resultSet.getString("lender_name"));
                    request.setBook_name(resultSet.getString("book_name"));
                    requests.add(request);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting Requests by borrow id: " + e.getMessage());
        }
        return requests;
    }
    @Override
    public List<Request> getAll() {
        return new ArrayList<>(requests);
    }

//    @Override
//    public List<Request> getByBorrower(User borrower) {
//        List<Request> BorrowerRequests = new ArrayList<>();
//        for(Request request : requests){
//            if(request.getBorrower().equals(borrower)){
//                BorrowerRequests.add(request);
//            }
//        }
//        return BorrowerRequests;
//    }
//
//    @Override
//    public List<Request> getByLender(User lender) {
//        List<Request> LenderRequests = new ArrayList<>();
//        for(Request request: requests){
//            if(request.getLender().equals(lender)){
//                LenderRequests.add(request);
//            }
//        }
//        return LenderRequests;
//    }



//    @Override
//    public void accept(int requestId) {
//        Request request = getById(requestId);
//        if(request!= null){
//            request.setStatus(Status.ACCEPTED);
//            System.out.println("Request accepted successfully");
//
//        }else{
//            System.out.println("Request Not Found !!");
//        }
//
//
//    }
//
//    @Override
//    public void reject(int requestId) {
//        Request request = getById(requestId);
//        if(request!=null){
//            request.setStatus(Status.REJECTED);
//            System.out.println("Request rejected!!");
//        }else{
//            System.out.println("Request Not Found !!");
//        }
//    }

//    public List<Request> getRequestHistory(User user) {
//        return requests.stream()
//                .filter(req -> req.getBorrower().equals(user) || req.getLender().equals(user))
//                .collect(Collectors.toList());
//    }
}
