package shop.dao.jdbc;

import org.junit.jupiter.api.Test;
import shop.web.entity.Food;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


public class FoodRowMapperTest {
    @Test
    public Food mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int price = resultSet.getInt("price");
        String comment = resultSet.getString("comment");
        String name = resultSet.getString("name");
        Timestamp date = resultSet.getTimestamp("date");
        Food food = Food.builder()
                .id(id)
                .name(name)
                .comment(comment)
                .price(price)
                .date(date.toLocalDateTime())
                .build();
        return food;
    }
}
