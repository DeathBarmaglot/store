package shop.service;

import shop.dao.UserDao;
import shop.web.entity.User;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

public class SecurityService {

    private final UserDao userDao;
    private final List<String> userTokens = new ArrayList<>();

    public SecurityService(UserDao userDao) {
        this.userDao = userDao;
    }



    public String signIn(User user) {
        return null;
    }

    private String signOut(User user) {
        return user.getEmail();
    }

    private String createToken() {
        return null;

    }

    public boolean isLoggedIn(String token) {
//        Cookie cookie = new Cookie();
        return true;
    }
}