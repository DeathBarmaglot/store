package shop.web.servlet;

import shop.service.UserService;
import shop.service.FoodService;
import shop.web.utils.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class MainPage extends HttpServlet {
    private final FoodService foodService;
    private final UserService userService;
    PageGenerator pageGenerator = PageGenerator.instance();

    public MainPage(FoodService foodService, UserService userService) {
        this.foodService = foodService;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HashMap<String, Object> params = new HashMap<>();
        try {
            params.put("foods", foodService.findAllFood());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.getWriter().write(pageGenerator.getPage("main.html", params));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            foodService.removeFood(Integer.parseInt(req.getParameter("id").replaceAll("([\\ud800-\\udbff\\udc00-\\udfff])", "")));
            resp.sendRedirect("/main");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

