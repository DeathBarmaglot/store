package shop.dao.jdbc.mapper;

import shop.web.entity.Food;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class FoodMapper {
    public Food mapRow(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String comment = resultSet.getString("comment");
        String email = resultSet.getString("email");
        int price = resultSet.getInt("price");
        Timestamp date = resultSet.getTimestamp("date");
        return Food.builder()
                .id(id)
                .name(name)
                .comment(comment)
                .price(price)
                .email(email)
                .date(date.toLocalDateTime())
                .build();
    }
}
