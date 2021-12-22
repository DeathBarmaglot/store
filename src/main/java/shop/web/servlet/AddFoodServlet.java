package shop.web.servlet;

import shop.web.entity.Food;
import shop.service.FoodService;
import shop.web.utils.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddFoodServlet extends HttpServlet {
    private final FoodService foodService;
    PageGenerator pageGenerator = PageGenerator.instance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //TODO isAuth  String token = getToken(req);
        boolean isAuth = true; //userService.isAuth(token, userToken);
        if (isAuth) {
            HashMap<String, Object> params = new HashMap<>();
            String page = pageGenerator.getPage("add.html", params);
            resp.getWriter().write(page);
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            Food food = getFoodFromRequest(req);
            foodService.addFood(food);
            resp.sendRedirect("/main");
        } catch (Exception e) {
            String error = "<div>Your food not been added</div>";
            Map<String, Object> param = Map.of("error", error);
            String page = pageGenerator.getPage("add.html", param);
            resp.getWriter().write(page);
        }
    }

    private Food getFoodFromRequest(HttpServletRequest req) {
        return Food.builder()
                .name(req.getParameter("name"))
                .comment(req.getParameter("comment"))
                .email(req.getParameter("email"))
                .price(Integer.parseInt(req.getParameter("price")))
                .build();
    }

    public AddFoodServlet(FoodService foodService) {
        this.foodService = foodService;
    }
}
