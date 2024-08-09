package Persistence;

import Models.Request;
import Models.User;

import java.util.List;

public interface RequestDAO {
    Request getById(int id);
    List<Request> getAll();

//    List<Request> getByBorrower(User borrower);
//    List<Request> getByLender(User lender);

    String addRequest(Request request);
    List<Request> getRequestsByBorrowId(int borrow_id);
//    void submit(Request request);
//    void accept (int requestId);
//
//    void reject(int request);



}
