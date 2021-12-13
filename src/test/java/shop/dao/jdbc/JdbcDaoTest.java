package shop.dao.jdbc;

import org.junit.jupiter.api.Test;
import shop.web.entity.Food;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JdbcDaoTest {
    @Test
    public void testData() {
        JdbcFoodDao jdbcDao = new JdbcFoodDao();
        List<Food> foods = jdbcDao.findAllFood();
        assertFalse(foods.isEmpty());
        for (Food food : foods) {
            assertNotEquals(0, food.getId());
            assertNotNull(food.getId());
            assertNotNull(food.getDate());
            assertNotNull(food.getName());
            assertNotNull(food.getPrice());
        }
    }
}