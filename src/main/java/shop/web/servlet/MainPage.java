package shop.web.servlet;

import lombok.SneakyThrows;
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

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        PageGenerator pageGenerator = PageGenerator.instance();

            List<Food> foods = null;
            try {
                foods = foodService.findAllFood();
            } catch (SQLException e) {
                e.printStackTrace();
            }
//        TaskType[] taskType = TaskType.values();

            HashMap<String, Object> params = new HashMap<>();
            params.put("foods", foods);
//        params.put("taskType", taskType);
//        System.out.println(params);


        resp.getWriter().write(pageGenerator.getPage("main.html", params));
        }

////    @Override
////    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        try {
////            Food food = getFoodFromRequest(req);
////            foodService.addFood(food);
////
//////            resp.sendRedirect("");
////        } catch (Exception e) {
////            PageGenerator pageGenerator = PageGenerator.instance();
////            String page = pageGenerator.getPage("remove.html", new HashMap<>());
////            resp.getWriter().write(page);
////            resp.getWriter().write("<div>Your food not been changed</div>");
////        }
//    }
//    private Food getFoodFromRequest(HttpServletRequest req) {
//        return Food.builder()
//                .id(Integer.parseInt(req.getParameter("id")))
//                .build();
//    }
}

