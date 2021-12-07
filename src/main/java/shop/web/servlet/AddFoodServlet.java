package shop.web.servlet;

import shop.Food;
import shop.FoodService;
import shop.web.util.PageGenerator;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.HashMap;

public class AddFoodServlet extends HttpServlet {
    private final FoodService foodService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("add_food.html");
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Food food = getFoodFromRequest(req);
            foodService.add(food);
            resp.sendRedirect("reviews_list.html");
        } catch (Exception e){
            PageGenerator pageGenerator = PageGenerator.instance();
            String page = pageGenerator.getPage("add_food.html", new HashMap<>());
            resp.getWriter().write(page);
            resp.getWriter().write("<div>Your food not bee added</div>");
        }
    }
private Food getFoodFromRequest(HttpServletRequest req){
        return Food.builder()
                .name(req.getParameter("name"))
                .price(Integer.parseInt(req.getParameter("price")))
                .build();
}

    public AddFoodServlet(FoodService foodService) {this.foodService = foodService;}

}
