package shop.dao;

import shop.web.entity.Food;
import shop.web.entity.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {

//    void removeUser(int id) throws SQLException;

    List<User> findUserByName(String name);

    boolean isUserExists(String name);

    void addUser(User user)throws SQLException;

    List<User> findAllUsers();
}