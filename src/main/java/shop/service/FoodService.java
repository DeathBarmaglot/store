package shop;

import shop.dao.FoodDao;

import java.sql.SQLException;
import java.util.List;

public class FoodService {
    private FoodDao foodDao;
    public FoodService(FoodDao foodDao){
        this.foodDao = foodDao;
    }
    public List<Food> findAll() throws SQLException {
    List<Food> foods = foodDao.findAll();
        System.out.println(foods.size());
        return foods;
    }
}
