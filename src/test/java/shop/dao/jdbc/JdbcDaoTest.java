package shop.dao.jdbc;

import org.junit.jupiter.api.Test;
import shop.Food;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JdbcDaoTest {
    @Test
    public void testData() {
        JdbcDao jdbcDao = new JdbcDao();
        List<Food> foods = jdbcDao.findAll();
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