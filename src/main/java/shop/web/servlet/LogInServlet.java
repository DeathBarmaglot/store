package shop.web.servlet;

import shop.web.entity.User;
import shop.service.UserService;
import shop.web.utils.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static shop.web.utils.WebUtil.getUser;

public class LogInServlet extends HttpServlet {
    private final UserService userService;
    PageGenerator pageGenerator = PageGenerator.instance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String page = pageGenerator.getPage("index.html");
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/main");
//        User user = getUser(req);
//        System.out.println(user);
//
//        String page = pageGenerator.getPage("main.html");
//        resp.getWriter().write(page);
    }

     public LogInServlet (UserService userService, List<String> userToken) {
        this.userService = userService;
     }
}
