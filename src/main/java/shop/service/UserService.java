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

    public UserService() {
        userDao = null;
    }

    public List<String> findAllUsers() {
        return userDao.findAllUsers();
    }

    public boolean isUserExists(String email) {
        return userDao.isUserExists(email);
    }

     public void addUser(User user) throws SQLException {
        LocalDateTime localDateTime = LocalDateTime.now();
        user.setDate(localDateTime);
        userDao.addUser(user);
//        System.out.println("User added " + user); //name=
    }

}
