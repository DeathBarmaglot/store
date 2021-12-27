package shop.web.utils;

import shop.web.entity.Food;
import shop.web.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.UUID;

public class WebUtil {
    public static User getUser(HttpServletRequest req) {
        return User.builder()
                .name(req.getParameter("name"))
                .email(req.getParameter("email"))
                .pwd(req.getParameter("pwd"))
                .date(LocalDateTime.parse(req.getParameter("date")))
                .build();
    }

    public static String generator() {
        String str = String.valueOf(UUID.randomUUID());
        String filterStr = String.valueOf(str.hashCode());
        return filterStr.replaceAll("-", "");
    }
}

