package shop.web.servlet;

import shop.web.entity.Food;
import shop.service.FoodService;
import shop.web.utils.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditFoodServlet extends HttpServlet {
    private final FoodService foodService;
    private final PageGenerator pageGenerator = PageGenerator.instance();

    public EditFoodServlet(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String page = pageGenerator.getPage("edit_food.html");
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        long id = ((Integer.parseInt(req.getParameter("id")
                .replaceAll("([\\ud800-\\udbff\\udc00-\\udfff])", ""))));
        Food food = foodService.findFoodById(id);

        try {
            foodService.editFood(food);
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        resp.sendRedirect("/main");
//        String page = pageGenerator.getPage("main.html");
//        resp.getWriter().write(page);


// pageGenerator.getPage("main.html", new HashMap<>());
//
//        resp.sendRedirect("/main");
//        resp.getWriter().write(page);
//
//    } catch (Exception e) {
//        e.printStackTrace();
//        resp.sendRedirect("/edit");
//        resp.getWriter().write("<div>Your food not been changed</div>");
//        String page = pageGenerator.getPage("edit_food.html", new HashMap<>());
//        resp.getWriter().write(page);
//
//    }


//    private Food getFoodFromRequest(HttpServletRequest req) {
//        Food build = Food.builder()
//                .name(req.getParameter("name"))
//                .comment(req.getParameter("comment"))
//                .price(Integer.parseInt(req.getParameter("price")))
//                .build();
//        return build;
//    }
    }
}
