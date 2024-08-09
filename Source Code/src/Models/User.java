package Models;

import java.util.List;

public class User {
    private  int id;

    private static int generatedId;
    private String name;

    private String username;

    private String password;

    private List<Book> borrowedBooks;

    private List<Book> ownedBooks;

    private List<Request> submittedRequests;

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    public User(String name ,String username, String password) {
        this.id = generatedId++;
        this.name = name;
        this.username=username;
        this.password = password;
    }


    //region setters

    public void setId(int id){this.id=id;}
    public void setName(String _name){
        name= _name;
    }
    public void setUsername(String _username){
        username = _username;
    }

    public void setPassword(String _password){
        password = _password;
    }

    public void setBorrowedBooks(List<Book> _borrowedBooks){
        borrowedBooks = _borrowedBooks;
    }

    public void setOwnedBooks(List<Book> _ownedBooks){
        ownedBooks = _ownedBooks;
    }

    public void setSubmittedRequests(List<Request> _submittedRequests){
        submittedRequests = _submittedRequests;
    }
    //endregion

    //region getters
    public String getName(){
        return name;
    }
    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public List<Book> getBorrowedBooks(){
        return borrowedBooks;
    }

    public List<Book> getOwnedBooks(){
        return ownedBooks;
    }

    public List<Request> getSubmittedRequests(){
        return submittedRequests;
    }

    public int getId(){
        return id;
    }


    //endregion
}
