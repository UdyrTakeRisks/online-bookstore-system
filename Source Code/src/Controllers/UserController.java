package Controllers;


import Models.User;
import Services.UserService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserController {
    private UserService userService = new UserService();

    public UserController() {
    }

    ;
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }


    public int getUserId(String userName) {
        return userService.getIdByUserName(userName);
    }

    public String loginUser(User user) {

//        UserService userService = new UserService();

        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            return ("Username and password cannot be empty.");

        }

        User retrievedUser = userService.getByUserName(user.getUsername());

        if (retrievedUser == null) {
            return ("404 Error: User not found.");

        }

        String MSG = "";
        String hashedInputPassword = hashPassword(user.getPassword());
        if (hashedInputPassword.equals(retrievedUser.getPassword())) {
            MSG = "Login successful.";
        } else {
            MSG = "401 Error: Unauthorized.";
        }
        return MSG;
    }


    public String registerUser(User user) {

        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            return ("Username and password cannot be empty.");

        }

        if (userService.getByUserName(user.getUsername()) != null) {
            return ("User with username '" + user.getUsername() + "' already exists.");

        }

        String hashedPassword = hashPassword(user.getPassword());
        user.setPassword(hashedPassword);

        userService.create(user);
        return ("User registered successfully.");
    }


    private String hashPassword(String password) {
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());


            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

            return null;
        }
    }
}
