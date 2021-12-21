package shop.dao;

import shop.web.entity.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void removeUser(String email) throws SQLException;

    boolean isUserExists(String email);

    void addUser(User user)throws SQLException;

    List<String> findAllUsers();

    List<User> findUserByEmail(String email);
}
