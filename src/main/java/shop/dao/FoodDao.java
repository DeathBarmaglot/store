package shop.dao;

import shop.web.entity.Food;
import java.sql.SQLException;
import java.util.List;

public interface FoodDao {

    List<Food> findAllFood() throws SQLException;

    void addFood(Food food) throws SQLException;

    void editFood(Food food) throws SQLException;

    void removeFood(long id) throws SQLException;

    List<Food> findFoodByName(String name);

    Food findFoodById(long id);

}
