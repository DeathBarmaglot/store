package shop.dao;

import shop.web.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void removeUser(String email) throws SQLException;

    boolean isUserExists(User user);

    boolean isAuth(User user, List<String> userTokens);

    void addUser(User user)throws SQLException;

    List<User> findAllUsers();

    User findUserByEmail(String email);

}
