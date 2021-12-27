package shop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.service.FoodService;
import shop.web.entity.Food;
import shop.web.utils.PageGenerator;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;

@Controller
public class FoodController {

    @Autowired
    private FoodService foodService;

    PageGenerator pageGenerator = PageGenerator.instance();

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    @ResponseBody
    protected String findAll() throws SQLException {
        HashMap<String, Object> params = new HashMap<>();
        params.put("foods", foodService.findAllFood());
        return (pageGenerator.getPage("main.html", params));
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    @ResponseBody
    protected String getAddNewFood() {
        return pageGenerator.getPage("add.html", new HashMap<>());
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    protected String postAddNewFood(HttpServletRequest req) throws SQLException {
        foodService.addFood(getFood(req));
        return "redirect:/main";
    }

    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    protected String updateFood(HttpServletRequest req) throws SQLException {
        foodService.editFood(getAllFood(req));
        return "redirect:/main";
    }

    @RequestMapping(path = "/main", method = RequestMethod.POST)
    public String deleteProduct(HttpServletRequest req) throws SQLException {
        foodService.removeFood(Integer.parseInt(req.getParameter("id")));
        return "redirect:/main";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    protected String logIn() {
        HashMap<String, Object> params = new HashMap<>();
        return (pageGenerator.getPage("login.html", params));
    }

    public void setFoodService(FoodService foodService) {
        this.foodService = foodService;
    }

    private Food getAllFood(HttpServletRequest req) {
        return Food.builder()
                .id(Integer.parseInt(req.getParameter("id")))
                .name(req.getParameter("name"))
                .comment(req.getParameter("comment"))
                .price(Integer.parseInt(req.getParameter("price")))
                .build();
    }

    private Food getFood(HttpServletRequest req) {
        return Food.builder()
                .name(req.getParameter("name"))
                .comment(req.getParameter("comment"))
                .price(Integer.parseInt(req.getParameter("price")))
                .build();
    }

}
