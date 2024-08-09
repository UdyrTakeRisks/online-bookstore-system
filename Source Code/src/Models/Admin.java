package Models;

import java.util.List;

public class Admin {
    private int id;
    private String username;

    private String password;
    private List<Book> ManagesBooks;

    //region setters


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setManagesBooks(List<Book> managesBooks) {
        ManagesBooks = managesBooks;
    }
    //endregion

    //region getters

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public List<Book>getManagesBooks(){
        return ManagesBooks;
    }


}
