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
        List<User> users = null;
        List<Food> foods = null;
        try {
            foods = foodService.findAllFood();
        } catch (SQLException e) {
            e.printStackTrace();
//                foodService.createFood();
        }
//        TaskType[] taskType = TaskType.values();
        HashMap<String, Object> params = new HashMap<>();
        params.put("foods", foods);
//        params.put("taskType", taskType);
        resp.getWriter().write(pageGenerator.getPage("main.html", params));
    }
}

