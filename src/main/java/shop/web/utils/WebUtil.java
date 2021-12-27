package shop.web.utils;

import shop.web.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

public class WebUtil {
    public static User getUser(HttpServletRequest req) {
        return User.builder()
                .name(req.getParameter("name"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .role(req.getParameter("role"))
                .auth(Boolean.parseBoolean(req.getParameter("auth")))
                .build();
    }

    public static String generator() {
        String str = String.valueOf(UUID.randomUUID());
        String filterStr = String.valueOf(str.hashCode());
        return filterStr.replaceAll("-", "");
    }
}

