package shop.web.servlet;

import shop.service.SecurityService;
import shop.web.entity.User;
import shop.service.UserService;
import shop.web.utils.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LogInServlet extends HttpServlet {
    private SecurityService securityService;
    PageGenerator pageGenerator = PageGenerator.instance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String page = pageGenerator.getPage("index.html");
        resp.getWriter().write(page);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = User.builder()
                .email(req.getParameter("email"))
                .pwd(req.getParameter("pwd"))
                .build();

        String token;
        boolean isAuth = securityService.isLoggedIn(user.getEmail());
        if (!isAuth) {

            try {
                token = securityService.signIn(user);
                Cookie cookie = new Cookie("user-token", token);
                resp.addCookie(cookie);
                resp.sendRedirect("/");

            } catch (Exception e) {
//                String error = "Invalid email or password!";
//                String page = pageGenerator("login.html", error);
//                resp.getWriter().write(page);
            }
        } else {
            resp.sendRedirect("/");
        }

    }

     public LogInServlet (SecurityService securityService) {
        this.securityService = securityService;
     }
}
