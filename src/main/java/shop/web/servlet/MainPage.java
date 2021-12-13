package shop.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.avro.mapred.tether.TaskType;
import shop.web.entity.Food;
import shop.service.FoodService;
import shop.web.util.PageGenerator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static shop.web.util.WebUtil.getFood;

@WebServlet("/main")
public class MainPage extends HttpServlet {
    private final FoodService foodService;

    public MainPage(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        List<Food> foods = null;

        //TODO
        // getFood(req);

//        TaskType[] taskType = TaskType.values();

        if (req.getParameter("name") != null) {
            foods = foodService.findFoodByName(req.getParameter("goods"));
        } else {
            try {
                foods = foodService.findAllFood();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        PageGenerator pageGenerator = PageGenerator.instance();
        HashMap<String, Object> params = new HashMap<>();
        params.put("foods", foods);
        System.out.println("main get "+ foods);
        String page = pageGenerator.getPage("list_food.html", params);
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



    }
}


