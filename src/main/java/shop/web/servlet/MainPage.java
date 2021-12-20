package shop.web.servlet;

import shop.service.UserService;
import shop.web.entity.Food;
import shop.service.FoodService;
import shop.web.utils.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class MainPage extends HttpServlet {
    private final FoodService foodService;
    private final UserService userService;

    public MainPage(FoodService foodService, UserService userService) {
        this.foodService = foodService;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();

        List<Food> foods = null;
        try {
            foods = foodService.findAllFood();
//            System.out.println(foods); //list all food
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = ((Integer.parseInt(req.getParameter("id")
                .replaceAll("([\\ud800-\\udbff\\udc00-\\udfff])", ""))));
        Food food = foodService.findFoodById(id);

        try {
            foodService.editFood(food);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

