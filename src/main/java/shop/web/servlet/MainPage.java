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
    PageGenerator pageGenerator = PageGenerator.instance();

    public MainPage(FoodService foodService, UserService userService) {
        this.foodService = foodService;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

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

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//
//        Food food = getFoodFromRequest(req);
//        System.out.println(food.getId());
//
//        try {
//            foodService.editFood(food);
//            HashMap<String, Object> param = new HashMap<>();
//            String page = pageGenerator.getPage("main.html", param);
//            resp.getWriter().write(page);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        resp.sendRedirect("/main");
//        System.out.println(food);
//    }
//
//    private Food getFoodFromRequest(HttpServletRequest req) {
//        Food build = Food.builder()
//                .id(Integer.parseInt(req.getParameter("id")))
//                .name(req.getParameter("name"))
//                .comment(req.getParameter("comment"))
//                .price(Integer.parseInt(req.getParameter("price")))
//                .build();
//        return build;
//    }

}

