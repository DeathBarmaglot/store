package shop.dao.jdbc;

//CREATE TABLE users (name VARCHAR(50), email VARCHAR(50), pwd VARCHAR(50), date DATE);
//INSERT INTO users (name, email, pwd, date) VALUES ("admin", "qwerty@gmail.com", "admin", 12-12-2021);
//SELECT name, email, pwd FROM users *;


import shop.dao.jdbc.mapper.UserMapper;
import shop.web.entity.User;
import shop.dao.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcUserDao implements UserDao {
    private static final UserMapper USER_MAPPER = new UserMapper();
    private static final String NEW_USER_SQL = "CREATE TABLE users (name VARCHAR(50), email VARCHAR(50), pwd VARCHAR(50), date DATE);";
    private static final String ADD_USER_SQL = "INSERT INTO users (name, email, pwd, date) VALUES (?, ?, ?, ?);";
    private static final String FIND_USER_SQL = "SELECT name, email, pwd FROM users WHERE email =?;";
    private static final String FIND_USERS_SQL = "SELECT name, email, pwd FROM users *;";
    private static final String REMOVE_USER_SQL = "DELETE FROM users (email) VALUES (?);";


    @Override
    public void removeUser(String email) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_SQL + " ")) {
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> findUserByName(String name) throws SQLException {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_SQL);
             ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            List<User> users = new ArrayList<>();
            System.out.println(users);
            while (resultSet.next()) {
                User user = USER_MAPPER.mapRow(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean isUserExists(String user) {
        // List<User> users = new findAllUsers();
        //TODO isUserExists -> findAllUsers

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_SQL);
            preparedStatement.setString(1, user);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String pwd = USER_MAPPER.mapRow(resultSet).getPwd();
                if (pwd.equals(resultSet.getString("password"))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addUser(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_SQL + " ")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPwd());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(user.getDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> findAllUsers() {

        List<String> users = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USERS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {

                String user = USER_MAPPER.mapRow(resultSet).getName();
                users.add(user);
                System.out.println(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User not found");
        return null;
    }

    private Connection getConnection() throws SQLException {
        final String DB_URL = "jdbc:postgresql://192.168.31.249:5432/store";
        return DriverManager.getConnection(DB_URL, "admin", "root");
    }
}
