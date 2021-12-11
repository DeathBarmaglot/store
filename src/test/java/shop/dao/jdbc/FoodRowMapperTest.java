package shop.dao.jdbc;

import org.junit.jupiter.api.Test;
import shop.Food;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class FoodRowMapperTest {
    @Test
    public Food mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int price = resultSet.getInt("price");
        String name = resultSet.getString("name");
        Timestamp date = resultSet.getTimestamp("date");
        Food food = Food.builder()
                .id(id)
                .name(name)
                .price(price)
                .date(date.toLocalDateTime())
                .build();
        return food;
    }
}
