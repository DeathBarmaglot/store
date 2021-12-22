package shop.service;

import shop.web.entity.Food;
import shop.dao.FoodDao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static shop.web.utils.WebUtil.generator;

public class FoodService {
    private final FoodDao foodDao;

    public FoodService(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    public List<Food> findAllFood() throws SQLException {
        List<Food> foods;
        foods = foodDao.findAllFood();
        return foods;
    }

    public void addFood(Food food) throws SQLException {
        food.setDate(LocalDateTime.now());
        food.setEmail(food.getEmail());
        food.setId(generateId());
        foodDao.addFood(food);
        System.out.println(food);
    }

    public void editFood(Food food) throws SQLException {
        food.setDate(LocalDateTime.now());
        food.setPrice(food.getPrice());
        food.setName(food.getName());
        food.setComment(food.getComment());
        foodDao.editFood(food);
//        System.out.println("Food edited " + food);
    }

    public void removeFood(long id) throws SQLException {
        foodDao.removeFood(id);
        System.out.println("Food remove");
    }

    public Food findFoodById(long id) {
        Food food = foodDao.findFoodById(id);
        System.out.println(("found " + food));
        return food;
    }

    private long generateId() {
        return Integer.parseInt(generator().replaceAll("([\\ud800-\\udbff\\udc00-\\udfff])", ""));
    }
}
