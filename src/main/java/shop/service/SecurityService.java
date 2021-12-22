package shop.service;

import shop.dao.UserDao;
import shop.web.entity.User;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;

public class SecurityService {

    private final UserService userService;
    private final  FoodService foodService;
//    private final List<String> userTokens = new ArrayList<>();

    public SecurityService(UserService userService, FoodService foodService) {
        this.userService = userService;
        this.foodService = foodService;
    }


//    private String signOut(User user) {
//        return user.getEmail();
//    }

//    private String createToken(User user ) {
//           return user.getEmail();
//    }

    public boolean isLoggedIn(String email) {
        return  userService.isUserExists(email);
    }

    public String signIn(User user) {
        return  null;
    }
}