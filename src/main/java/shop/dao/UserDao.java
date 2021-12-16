package shop.dao;

import shop.web.entity.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void removeUser(int id) throws SQLException;

    List<User> findUserByName(String name) throws SQLException;

    boolean isUserExists(String email);

    void addUser(User user)throws SQLException;

    List<String> findAllUsers();
}
