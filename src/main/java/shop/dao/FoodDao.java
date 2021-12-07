package shop.dao;

import shop.Food;
import java.sql.SQLException;
import java.util.List;

public interface FoodDao {
    List<Food> findAll() throws SQLException;

    void add(Food food);

    List<Food> findByName(String name);
}
