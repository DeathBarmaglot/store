package shop.web.servlet;

import shop.service.UserService;
import shop.web.entity.User;
import shop.web.utils.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static shop.web.utils.WebUtil.getUser;

public class AdminServlet extends HttpServlet {
    private final UserService userService;
    PageGenerator pageGenerator = PageGenerator.instance();

    public AdminServlet(UserService userService) {
        this.userService = userService;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> users = userService.findAllUsers();
        System.out.println("get" + req.getParameter("email"));
        HashMap<String, Object> params = new HashMap<>();
        params.put("users", users);
        resp.getWriter().write(pageGenerator.getPage("main.html", params));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            userService.removeUser(getUser(req).getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
