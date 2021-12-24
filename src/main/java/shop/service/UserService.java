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

    public List<String> findAllUsers() {
        return userDao.findAllUsers();
    }

     public void addUser(User user) throws SQLException {
        LocalDateTime localDateTime = LocalDateTime.now();
        user.setDate(localDateTime);
        userDao.addUser(user);
        System.out.println("User added " + user);
    }

    public List<User> findUserByName(String name) throws SQLException {
        List<User> userList = userDao.findUserByName(name);
        System.out.println(("Obtain" + name + userList));
        return userList;
    }

    public void isAuth(User user, List<String> userTokens) {
        System.out.println("isAuth");
    }
}
