package shop.web.servlet;

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
        try {
            foods = getFood(req);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        TaskType[] taskType = TaskType.values();

        HashMap<String, Object> params = new HashMap<>();
        params.put("foods", foods);
        params.put("taskType", taskType);

        resp.getWriter().write(pageGenerator.getPage("list.html", params));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            Food food = getFoodFromRequest(req);
            foodService.addFood(food);

            System.out.println("AddDoPost" + food);
        } catch (Exception e) {
            String error = "<div>Your food not been added</div>";
            Map<String, Object> param = Map.of("error", error);
            String page = pageGenerator.getPage("list.html", param);
            resp.getWriter().write(page);
        }

        PageGenerator pageGenerator = PageGenerator.instance();
        HashMap<String, Object> params = new HashMap<>();
        try {
            params.put("foods", foodService.findAllFood());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String page = pageGenerator.getPage("list.html", params);
        resp.getWriter().write(page);

    }

    private List<Food> getFood(HttpServletRequest req) throws SQLException {
        List<Food> foods;
//        String taskTypeString = req.getParameter("task-filter");
//        FoodRequest foodRequest = new FoodRequest();
//
//        if (taskTypeString != null && ! taskTypeString.equals("All")) {
//            TaskType taskType = TaskType.getById(taskTypeString);
//            foodRequest.setTaskType(taskType);
//        }
        foods = foodService.findAllFood();
        System.out.println("show get" + foods);
        return foods;
    }

    private Food getFoodFromRequest(HttpServletRequest req) {
        return Food.builder()
                .name(req.getParameter("name"))
                .price(Integer.parseInt(req.getParameter("price")))
                .build();
    }
}

