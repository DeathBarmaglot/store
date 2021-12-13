package shop.dao.jdbc;

import shop.web.entity.User;
import shop.dao.UserDao;
import shop.dao.jdbc.mapper.FoodMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcUserDao implements UserDao {
    private static final FoodMapper USER_MAPPER = new FoodMapper();
    private static final String NEW_SQL = "CREATE TABLE users (name VARCHAR(50), email VARCHAR(50), pwd VARCHAR(50), token VARCHAR(50)  date DATE);";
    private static final String ADD_USER_SQL = "INSERT INTO user (name, email, pwd, token, date) VALUES (?, ?, ?, ?, ?);";
    private static final String FIND_USER_SQL = "SELECT name, email, pwd FROM users WHERE name =?;";
    private static final String FIND_USERS_SQL = "SELECT name, email, pwd FROM users *;";

    @Override
    public List<User> findUserByName(String name) {

//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_SQL);
//             ResultSet resultSet = preparedStatement.executeQuery();
//        ) {
//            List<User> users = new ArrayList<>();
//            while (resultSet.next()) {
//                User user = USER_MAPPER.mapRow(resultSet);
//                users.add(user);
//            }
//            return users;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    public boolean isUserExists(String user) {

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_SQL);
            //preparedStatement.setString(1, user.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();

//            if (resultSet.next()) {
//                String pwd = USER_MAPPER.mapRow(resultSet).getPwd();
//                if (pwd.equals(resultSet.getString("password"))) {
//                    return true;
//                }
//            }
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
            preparedStatement.setString(4, UUID.randomUUID().toString());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(user.getDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }


    private Connection getConnection() throws SQLException {
        final String DB_URL = "jdbc:postgresql://192.168.31.249:5432/store";
        return DriverManager.getConnection(DB_URL, "admin", "root");
    }
}
