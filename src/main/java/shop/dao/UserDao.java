package shop.dao;

import shop.web.entity.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    void removeUser(String email) throws SQLException;

    List<User> findUserByEmail(String name) throws SQLException;

    void addUser(User user)throws SQLException;

    List<String> findAllUsers();
}
