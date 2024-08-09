package Services;

import Models.Request;
import Models.User;
import Persistence.DBConnection;
import Persistence.UserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UserService  implements UserDAO  {
    private static int nextUserId;

    private static int retrieveLastUsedIdFromDatabase() {
        int lastUsedId = 0;
        String sql = "SELECT MAX(id) AS max_id FROM Users";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                lastUsedId = resultSet.getInt("max_id");
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving last used ID from the database: " + e.getMessage());
            e.printStackTrace();
        }

        return lastUsedId + 1; // Increment by 1 to get the next available ID
    }


    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
//        int id = resultSet.getInt("user_id");
        String username = resultSet.getString("username");
        String name = resultSet.getString("name");

        String password = resultSet.getString("password");
        return new User(name ,username, password);
    }
    private int extractUserIdFromResultSet(ResultSet resultSet) throws SQLException {
        return resultSet.getInt("user_id");
    }
    @Override
    public User getById(int id) {
        String sql = "SELECT * FROM Users WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getByUserName(String username) {
        String sql = "SELECT * FROM Users WHERE username = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractUserFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int getIdByUserName(String username) {
        String sql = "SELECT user_id FROM Users WHERE username = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractUserIdFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                users.add(extractUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void create(User user) {
//        int nextId = retrieveLastUsedIdFromDatabase() + 1;
        String sql = "INSERT INTO Users (name, username, password) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, nextId);
            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            System.out.println("User created successfully.");
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void update(User user) {
        String sql = "UPDATE Users  SET username = ?, password = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User user) {
        String sql = "DELETE FROM Users   WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPasswordByUserName(String username) {
        String query = "SELECT password FROM users WHERE username = ?";

        try (   Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

        return null;
    }
}
