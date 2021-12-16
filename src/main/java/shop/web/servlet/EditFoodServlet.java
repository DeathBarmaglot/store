package shop.web.servlet;

import shop.web.entity.Food;
import shop.service.FoodService;
import shop.web.utils.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.HashMap;


public class EditFoodServlet extends HttpServlet {
    private final FoodService foodService;

    public EditFoodServlet(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("edit_food.html");
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Food food = getFoodFromRequest(req);
            foodService.addFood(food);
            resp.sendRedirect("edit_food.html");
        } catch (Exception e) {
            PageGenerator pageGenerator = PageGenerator.instance();
            String page = pageGenerator.getPage("edit_food.html", new HashMap<>());
            resp.getWriter().write(page);
            resp.getWriter().write("<div>Your food not been changed</div>");
        }
    }

    private Food getFoodFromRequest(HttpServletRequest req) {
        return Food.builder()
                .name(req.getParameter("name"))
                .price(Integer.parseInt(req.getParameter("price")))
                .build();
    }
}
