package Controllers;

import Models.Request;
import Models.User;
import Services.RequestService;

import java.util.List;

public class RequestController {
    private RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    public void addRequest(Request request) {
        requestService.addRequest(request);
    }

    public List<Request> getRequestsById(int borrow_id) {
        return requestService.getRequestsByBorrowId(borrow_id);
    }

//    public void acceptRequest(int requestId) {
//        requestService.accept(requestId);
//    }
//
//    public void rejectRequest(int requestId) {
//        requestService.reject(requestId);
//    }

//    public List<Request> getRequestByBorrower(User borrower) {
//        return requestService.getByBorrower(borrower);
//    }

//    public List<Request> getRequestByLender(User lender) {
//        return requestService.getByLender(lender);
//    }

    public List<Request> GetAllRequests() {
        return requestService.getAll();
    }
}
