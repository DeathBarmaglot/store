package shop.dao.jdbc.mapper;

import shop.web.entity.Food;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class FoodMapper {
    public Food mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String comment = resultSet.getString("comment");
        int price = resultSet.getInt("price");
        Timestamp date = resultSet.getTimestamp("date");
        return Food.builder()
                .id(id)
                .name(name)
                .comment(comment)
                .price(price)
                .date(date.toLocalDateTime())
                .build();
    }
}
