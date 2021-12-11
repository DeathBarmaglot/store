package shop.web.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shop.Food;
import shop.service.FoodService;
import shop.web.util.PageGenerator;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

@WebServlet("/add")
public class AddFoodServlet extends HttpServlet {
    private final FoodService foodService;
    PageGenerator pageGenerator = PageGenerator.instance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //TODO isAuth
        boolean isAuth = false;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userToken")) {
                    isAuth = true;
                    break;
                }
            }
        }
        if (isAuth) {
            HashMap<String, Object> params = new HashMap<>();
            //TODO foodType filter
            // params.put("foodType", foodType.values());
            resp.sendRedirect("/product");
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
            foodService.add(food);
            resp.sendRedirect("food_list.html");
        } catch (Exception e) {
            String error = "<div>Your food not been added</div>";
            Map<String, Object> param = Map.of("error", error);
            String page = pageGenerator.getPage("add_food.html", param);
            resp.getWriter().write(page);
        }
    }

    private Food getFoodFromRequest(HttpServletRequest req) {
        return Food.builder()
                .name(req.getParameter("name"))
                .price(Integer.parseInt(req.getParameter("price")))
                .build();
    }

    public AddFoodServlet(FoodService foodService) {
        this.foodService = foodService;
    }
}
