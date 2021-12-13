package shop.web.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shop.web.entity.Food;
import shop.service.FoodService;
import shop.web.util.PageGenerator;

import java.io.IOException;

import java.util.HashMap;

@WebServlet("/edit")
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
