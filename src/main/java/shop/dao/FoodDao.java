package shop.dao;

import shop.web.entity.Food;
import java.sql.SQLException;
import java.util.List;

public interface FoodDao {
    boolean isFoodExists(Food food) throws SQLException;

    List<Food> findAllFood() throws SQLException;

    void createFood() throws SQLException;

    void addFood(Food food) throws SQLException;

    void editFood(Food food) throws SQLException;

    void removeFood(int id) throws SQLException;

    List<Food> findFoodByName(String name);

    List<Food> findFoodById(int id);

}
