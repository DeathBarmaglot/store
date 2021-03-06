package shop.service;

import shop.web.entity.User;
import shop.dao.UserDao;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    public void addUser(User user) throws SQLException {
        userDao.addUser(user);
        System.out.println("User added " + user);
    }

    public List<User> findUserByEmail(String email) throws SQLException {
        List<User> userList = userDao.findUserByEmail(email);
        System.out.println(("Obtain" + email + userList));
        return userList;
    }

    public void isAuth(User user, List<String> userTokens) {
        System.out.println("isAuth");
    }

    public void removeUser(Long userId) {
    }
}
