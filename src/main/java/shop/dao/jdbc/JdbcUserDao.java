package shop.dao.jdbc;

import org.apache.commons.codec.digest.DigestUtils;
import shop.dao.jdbc.mapper.UserMapper;
import shop.web.entity.Food;
import shop.web.entity.User;
import shop.dao.UserDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JdbcUserDao implements UserDao {
    private static final UserMapper USER_MAPPER = new UserMapper();
    private static final String ADD_USER_SQL = "INSERT INTO users (name, email, pwd, date) VALUES (?, ?, ?, ?);";
    private static final String FIND_EMAIL_SQL = "SELECT email FROM users WHERE email =?;";
    private static final String FIND_USER_SQL = "SELECT name, email, pwd, date FROM users WHERE email =?;";
    private static final String FIND_USERS_SQL = "SELECT name, email, pwd FROM users *;";
    private static final String REMOVE_USER_SQL = "DELETE FROM users (email) VALUES (?);";

    @Override
    public void removeUser(String email) {
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(REMOVE_USER_SQL + " ")) {
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isUserExists(User user) {
        return getProps(FIND_USER_SQL).contains(user);
    }

    @Override
    public boolean isAuth(User user, List<String> userTokens) {
        return getProps(user, FIND_USER_SQL);
    }


    private boolean getProps(User user, String sql) {
        try (
                Connection conn = getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, user.getEmail());
                ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                String sole = resultSet.getString("date");
                String pwd = resultSet.getString("pwd");
                String hash = DigestUtils.md5Hex(sole + user.getPwd());
                if (pwd.equals(hash)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return false;
    }

    private List<User> getProps(String sql) {

        List<User> users = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                users.add(USER_MAPPER.mapRow((resultSet)));
            }
            return users;
        } catch (SQLException e) {
            System.err.println("Products not found");
            e.printStackTrace();
            throw new RuntimeException();
        }
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
    public List<User> findAllUsers() {
        return getProps(FIND_USERS_SQL);
    }

    @Override
    public User findUserByEmail(String email) {
        return findUserByEmail(FIND_EMAIL_SQL);
    }

    private Connection getConnection() throws SQLException {
        final String DB_URL = "jdbc:postgresql://192.168.31.249:5432/store";
        return DriverManager.getConnection(DB_URL, "admin", "root");
    }
}
