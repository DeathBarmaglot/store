package shop.web.servlet;

import shop.web.entity.Food;
import shop.service.FoodService;
import shop.web.utils.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class ShowList extends HttpServlet {
    private final FoodService foodService;

    public ShowList(FoodService foodService) {
        this.foodService = foodService;
    }

    PageGenerator pageGenerator = PageGenerator.instance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List<Food> foods = null;
        try {
            foods = foodService.findAllFood();
            System.out.println("show get" + foods);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        TaskType[] taskType = TaskType.values();

        HashMap<String, Object> params = new HashMap<>();
        params.put("foods", foods);
//        params.put("taskType", taskType);

        resp.getWriter().write(pageGenerator.getPage("list.html", params));
    }
}

