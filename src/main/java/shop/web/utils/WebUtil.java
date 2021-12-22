package shop.web.utils;

import shop.web.entity.Food;
import shop.web.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class WebUtil {
    public static User getUser(HttpServletRequest req) {
        return User.builder()
                .name(req.getParameter("name"))
                .email(req.getParameter("email"))
                .pwd(req.getParameter("pwd"))
                .date(req.getParameter("date"))
                .build();
    }

    public static Food getFood(HttpServletRequest req) {
        return Food.builder()
                .name(req.getParameter("name"))
                .comment(req.getParameter("comment"))
                .email(req.getParameter("email"))
                .price(Integer.parseInt(req.getParameter("price")))
                .build();
    }

    public static String generator() {
        String str = String.valueOf(UUID.randomUUID());
        String filterStr = String.valueOf(str.hashCode());
        return filterStr.replaceAll("-", "");
    }
}

