package shop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.service.FoodService;
import shop.web.entity.Food;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@Controller
public class FoodController {

    @Autowired
    private FoodService foodService;

    @RequestMapping(path = "/main", method = RequestMethod.GET)
    protected String findAll(Model model) throws SQLException {
        model.addAttribute("foods", foodService.findAllFood());
        return "main";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    protected String getAddNewFood() {
        return "add";
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

    @RequestMapping(path = "/", method = RequestMethod.GET)
    protected String logIn() {
        return "login";
    }


    @RequestMapping(path = "/", method = RequestMethod.POST)
    protected String auth(){
        return "redirect:/main";
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
