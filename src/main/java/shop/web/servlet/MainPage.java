package shop.web.servlet;

import shop.service.UserService;
import shop.web.entity.Food;
import shop.service.FoodService;
import shop.web.entity.User;
import shop.web.utils.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        resp.sendRedirect("/login");
//
//        List<User> users = null;
//        List<Food> foods = null;
//        try {
//            foods = foodService.findAllFood();
//        } catch (SQLException e) {
//            e.printStackTrace();
////                foodService.createFood();
//        }
////        TaskType[] taskType = TaskType.values();
//        HashMap<String, Object> params = new HashMap<>();
//        params.put("foods", foods);
////        params.put("taskType", taskType);
//        resp.getWriter().write(pageGenerator.getPage("main.html", params));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = getFoodFromRequest(req);
            userService.addUser(user);
            resp.sendRedirect("/main");
        } catch (Exception e) {
            String error = "<div>Your food not been added</div>";
            Map<String, Object> param = Map.of("error", error);
            String page = pageGenerator.getPage("add.html", param);
            resp.getWriter().write(page);
        }
    }
    private User getFoodFromRequest(HttpServletRequest req) {
        return User.builder()
                .name(req.getParameter("name"))
                .email(req.getParameter("email"))

                .build();
    }
}

