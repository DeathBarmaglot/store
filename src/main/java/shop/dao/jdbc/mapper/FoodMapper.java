package shop.dao.jdbc.mapper;

import shop.Food;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class FoodMapper {
    public Food mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int price = resultSet.getInt("price");
        Timestamp date = resultSet.getTimestamp("date");
        Food food = Food.builder().id(id).name(name).price(price).date(date.toLocalDateTime()).build();
        return food;
    }
}
