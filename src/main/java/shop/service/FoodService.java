package shop.service;

import shop.web.entity.Food;
import shop.dao.FoodDao;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

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
        LocalDateTime localDateTime = LocalDateTime.of(2014, 5, 29, 18, 41, 16);
        food.setDate(localDateTime);
        food.setId(generateId());
        foodDao.addFood(food);
    }

     public void createFood(Food food) throws SQLException {
         food.setDate(LocalDateTime.now());
         food.setId(generateId());
         foodDao.addFood(food);
    }

    private int generateId() {
        UUID id = UUID.randomUUID();
        String str=""+id;
        int uid=str.hashCode();
        String filterStr=""+uid;
        str=filterStr.replaceAll("-", "");
        return Integer.parseInt(str);
    }

    public void editFood(Food food) throws SQLException {
        food.setDate(LocalDateTime.of(2014, 5, 29, 18, 41, 16));
        food.setId(food.getId());
        foodDao.editFood(food);
        System.out.println("Food edited " + food);
    }

     public void removeFood(int id) throws SQLException {
        foodDao.removeFood(id);
        System.out.println("Food remove");
    }

    public List<Food> findFoodByName(String name){
        List<Food> foodList = foodDao.findFoodByName(name);
        System.out.println(("Fetch" + name + foodList));
        return foodList;
    }

    public List<Food> findFoodById(int id){
        List<Food> foodList = foodDao.findFoodById(id);
        System.out.println(("id " + id + foodList));
        return foodList;
    }
}
