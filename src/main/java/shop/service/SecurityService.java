package shop.service;

import org.apache.commons.codec.digest.DigestUtils;
import shop.web.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static shop.web.utils.WebUtil.getUser;

public class SecurityService {

    private final UserService userService;
    private final List<String> userTokens = new ArrayList<>();

    public SecurityService(UserService userService) {
        this.userService = userService;
    }

    private boolean signOut(User user) {
        return Boolean.parseBoolean(user.getEmail());
    }

    private String createToken(User user) {
        return DigestUtils.md5Hex(user.getPwd() + user.getDate());
    }

    public boolean isLoggedIn(String email) {
//        userService.isAuth(email, userTokens);
        return false;
    }

    public boolean signIn(HttpServletRequest req, List<String> userTokens) {
         userService.isAuth(getUser(req), userTokens);
        return false;
    }

    public List<String> addToken(String token) {
        userTokens.add(token);
        return userTokens;
    }
}
