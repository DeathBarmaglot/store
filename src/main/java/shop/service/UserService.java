package shop.service;

import shop.web.entity.User;
import shop.dao.UserDao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    public boolean isUserExists(User user) {
        return userDao.isUserExists(user);
    }

    public boolean isAuth(User user, List<String> userTokens) {
        return userDao.isAuth(user, userTokens);
    }

    public void removeUser(String email) throws SQLException {
        userDao.removeUser(email);
        System.out.println("Food remove");
    }

    public void addUser(User user) throws SQLException {
        user.setDate(LocalDateTime.now());
        userDao.addUser(user);
    }
}
