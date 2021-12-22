package shop.web.servlet;

import shop.service.SecurityService;
import shop.web.entity.User;
import shop.web.utils.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


import static shop.web.utils.WebUtil.generator;
import static shop.web.utils.WebUtil.getUser;

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

        User user = getUser(req);

        boolean isAuth = securityService.isLoggedIn(user.getEmail());

        try {
            if (!isAuth) {
                String token = generator();
                securityService.addToken(token);
                Cookie cookie = new Cookie("user-token", token);
                resp.addCookie(cookie);
                resp.sendRedirect("/main");
            } else {
                String error = "Invalid email or password!";
                Map<String, Object> param = Map.of("error", error);
                String page = pageGenerator.getPage("index.html", param);
                resp.getWriter().write(page);
            }
        } catch (Exception e) {
            resp.sendRedirect("/");
        }

    }

    public LogInServlet(SecurityService securityService) {
        this.securityService = securityService;
    }
}
