package shop.web.servlet;

import shop.service.FoodService;
import shop.web.entity.Food;
import shop.web.entity.User;
import shop.service.UserService;
import shop.web.utils.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RemoveServlet extends HttpServlet {
    private final FoodService foodService;
    PageGenerator pageGenerator = PageGenerator.instance();

    public RemoveServlet(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String page = pageGenerator.getPage("remove.html");
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            Food food = getFoodFromRequest(req);
            foodService.removeFood(food.getId());
            System.out.println(food.getId());
            foodService.findAllFood();
            String page = pageGenerator.getPage("main.html");
            resp.sendRedirect("/main");
            resp.getWriter().write(page);
            System.out.println("remove" + food);
        } catch (Exception e) {
            String error = "<div>Your food not been added</div>";
            Map<String, Object> param = Map.of("error", error);
            String page = pageGenerator.getPage("main.html", param);
            resp.getWriter().write(page);
        }
    }

    private Food getFoodFromRequest(HttpServletRequest req) {
        return Food.builder()
                .name(req.getParameter("name"))
                .comment(req.getParameter("comment"))
                .price(Integer.parseInt(req.getParameter("price")))
                .build();
    }

}
