package shop;

import shop.dao.FoodDao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class FoodService {
    private final FoodDao foodDao;

    public FoodService(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    public List<Food> findAll() throws SQLException {
        List<Food> foods = foodDao.findAll();
        System.out.println(foods.size());
        return foods;
    }

    public void add(Food food) {
        LocalDateTime localDateTime = LocalDateTime.now();
        food.setDate(localDateTime);
        foodDao.add(food);
        System.out.println("Food added");
    }

    public List<Food> findByName(String name){
        List<Food> foodList = foodDao.findByName(name);
        System.out.println(("Obtain" + name + foodList));
        return foodList;
    }
}
