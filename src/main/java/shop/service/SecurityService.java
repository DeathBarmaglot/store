package shop.service;

import org.apache.commons.codec.digest.DigestUtils;
import shop.web.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SecurityService {

    private final UserService userService;
    private final FoodService foodService;
    private final List<String> userTokens = new ArrayList<>();

    public SecurityService(UserService userService, FoodService foodService) {
        this.userService = userService;
        this.foodService = foodService;
    }

    private boolean signOut(User user) {
        return user.getEmail();
    }

    private String createToken(User user) {
        DigestUtils.md5Hex(user.getPwd() + user.getDate());
    }

    public boolean isLoggedIn(String email) {
        return userService.isAuth(user, userTokens);
    }

    public boolean signIn(HttpServletRequest req, List<String> userTokens) {
        userService.isAuth(req.get, userTokens);
        return
    }

    public List<String> addToken(String token) {
        userTokens.add(token);
        return userTokens;
    }
}
