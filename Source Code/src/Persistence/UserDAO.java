package Persistence;

import Models.User;

import java.util.List;

public interface UserDAO {
    User getById(int id);
    User getByUserName(String username);
    int getIdByUserName(String username);
    List<User> getAll();

    void create(User user);

    void update(User user);

    void delete(User user);

    String getPasswordByUserName(String username);


}
