package shop.dao.jdbc;

import shop.dao.jdbc.mapper.UserMapper;
import shop.web.entity.User;
import shop.dao.UserDao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDao implements UserDao {
    private static final UserMapper USER_MAPPER = new UserMapper();
    private static final String ADD_USER_SQL = "INSERT INTO users (name, email, pwd, date) VALUES (?, ?, ?, ?);";
    private static final String FIND_USER_SQL = "SELECT name, email, pwd FROM users WHERE email =?;";
    private static final String FIND_USERS_SQL = "SELECT name, email, pwd FROM users *;";
    private static final String REMOVE_USER_SQL = "DELETE FROM users (email) VALUES (?);";


    @Override
    public void removeUser(String email)  {
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(REMOVE_USER_SQL + " ")) {
            preparedStatement.setString(1, email);
//            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findUserByName(String name) {

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(FIND_USER_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()
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
            Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(FIND_USER_SQL);
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
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(ADD_USER_SQL + " ")) {
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

        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(FIND_USERS_SQL);
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
