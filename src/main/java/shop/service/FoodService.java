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

        return foodDao.findAllFood();
    }

     public void addFood(Food food) throws SQLException {
        food.setDate(LocalDateTime.now());
        food.setId(Long.parseLong(generator()));
        foodDao.addFood(food);
    }

    public void editFood(Food food) throws SQLException {
        food.setDate(LocalDateTime.now());
        food.setPrice(food.getPrice());
        food.setName(food.getName());
        food.setComment(food.getComment());
        foodDao.editFood(food);
    }

     public void removeFood(long id) throws SQLException {
        foodDao.removeFood(id);
    }

    public List<Food> findFoodByName(String name){
        List<Food> foodList = foodDao.findFoodByName(name);
        System.out.println(("Fetch" + name + foodList));
        return foodList;
    }

    public Food findFoodById(long id){
        Food food  = foodDao.findFoodById(id);
        System.out.println(("found " + food));
        return food;
    }
}
