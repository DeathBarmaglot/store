package shop.web.servlet;

import shop.service.FoodService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static shop.web.utils.WebUtil.getFood;

public class EditFoodServlet extends HttpServlet {
    private final FoodService foodService;

    public EditFoodServlet(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            foodService.editFood(getFood(req));
            resp.sendRedirect("/main");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
