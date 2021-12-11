package shop.web.servlet;

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

@WebServlet("/")
public class ShowAllRequestServlet extends HttpServlet {
    private final FoodService foodService;

    public ShowAllRequestServlet(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Food> foods = null;

        if (req.getParameter("name") != null) {
            foods = foodService.findByName(req.getParameter("goods"));
        } else {
            try {
                foods = foodService.findAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        PageGenerator pageGenerator = PageGenerator.instance();
        HashMap<String, Object> params = new HashMap<>();
        params.put("foods", foods);

        String page = pageGenerator.getPage("list_food.html", params);
        resp.getWriter().write(page);
    }
}


