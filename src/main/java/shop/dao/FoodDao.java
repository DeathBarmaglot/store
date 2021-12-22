package shop.dao;

import shop.web.entity.Food;

import java.sql.SQLException;
import java.util.List;

public interface FoodDao {

    void addFood(Food food) throws SQLException;

    void editFood(Food food) throws SQLException;

    void removeFood(long id) throws SQLException;

    Food findFoodById(long id);

    List<Food> findAllFood() throws SQLException;

    boolean isFoodExists(long id) throws SQLException;


}
