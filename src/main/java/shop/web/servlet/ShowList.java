package shop.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shop.Food;
import shop.service.FoodService;
import shop.web.util.PageGenerator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/list")
public class ShowList extends HttpServlet {
    private final FoodService foodService;

    public ShowList(FoodService foodService) {
        this.foodService = foodService;
    }
    PageGenerator pageGenerator = PageGenerator.instance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Food> foods = null;

        if (req.getParameter("name") != null) {
            foods = foodService.findByName(req.getParameter("goods"));
        } else {
            try {
                foods = foodService.findAll();
                System.out.println("show get" + foods);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put("foods", foods);
        String page = pageGenerator.getPage("list.html", params);
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Food food = getFoodFromRequest(req);
//            foodService.create(food);
            foodService.add(food);
            resp.sendRedirect("/list");
            System.out.println("AddDoPost" + food);
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
                .price(Integer.parseInt(req.getParameter("price")))
                .build();
    }

}


