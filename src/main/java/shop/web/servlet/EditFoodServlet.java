package shop.web.servlet;

import shop.web.entity.Food;
import shop.service.FoodService;
import shop.web.utils.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class EditFoodServlet extends HttpServlet {
    private final FoodService foodService;
    private final PageGenerator pageGenerator = PageGenerator.instance();

    public EditFoodServlet(FoodService foodService) {
        this.foodService = foodService;
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String page = pageGenerator.getPage("edit_food.html");
//        resp.getWriter().write(page);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        long id = Integer.parseInt(req.getParameter("id"));
//        Food food = foodService.findFoodById(id);
        Food food = getFoodFromRequest(req);
//        System.out.println(typeOf(food.getPrice()));

        try {
            foodService.editFood(food);
            resp.sendRedirect("/main");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(food);
    }
    private Food getFoodFromRequest(HttpServletRequest req) {
        return Food.builder()
                .id(Integer.parseInt(req.getParameter("id")))
                .name(req.getParameter("name"))
                .comment(req.getParameter("comment"))
                .price(Integer.parseInt(req.getParameter("price")))
                .build();
    }

}
