package shop.web.servlet;

import shop.service.UserService;
import shop.web.entity.Food;
import shop.service.FoodService;
import shop.web.entity.User;
import shop.web.utils.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static shop.web.utils.WebUtil.getUser;

public class MainPage extends HttpServlet {
    private final FoodService foodService;
    protected final UserService userService;
    PageGenerator pageGenerator = PageGenerator.instance();

    public MainPage(FoodService foodService, UserService userService) {
        this.foodService = foodService;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        resp.sendRedirect("/main");
        List<Food> foods = null;
        String email = "";
        try {
            foods = foodService.findAllFood();
            email = getUser(req).getEmail();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put("foods", foods);
        params.put("email", email);
        resp.getWriter().write(pageGenerator.getPage("main.html", params));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            User user = getUser(req);
            userService.addUser(user);
            resp.sendRedirect("/main");
        } catch (Exception e) {
            Map<String, Object> param = Map.of("error", "<div>Your food not been added</div>");
            String page = pageGenerator.getPage("add.html", param);
            resp.getWriter().write(page);
        }
    }

}

