package shop.web.servlet;

import shop.service.FoodService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RemoveServlet extends HttpServlet {
    private final FoodService foodService;

    public RemoveServlet(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("get" + req.getParameter("id"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println((req.getParameter("id").replaceAll("([\\ud800-\\udbff\\udc00-\\udfff])", "")));
        try {
            foodService.removeFood(Integer.parseInt(req.getParameter("id").replaceAll("([\\ud800-\\udbff\\udc00-\\udfff])", "")));
            resp.sendRedirect("/main");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
