package shop.dao.jdbc;

import shop.Food;
import shop.dao.DAO;
import shop.dao.jdbc.mapper.FoodMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDao implements DAO {
    private static final FoodMapper FOOD_MAPPER = new FoodMapper();
    private static final String FIND_ALL_SQL = "SELECT id, name, price, date FROM goods;";

    @Override
    public List<Food> findAll() {
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();){
            List<Food> foods = new ArrayList<>();
            while (resultSet.next()){
                Food food = FOOD_MAPPER.mapRow(resultSet);
                foods.add(food);
            }
            return foods;
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    private Connection getConnection() throws SQLException {
        final String DB_URL = "jdbc:postgresql://192.168.31.249:5432/store";
        return DriverManager.getConnection(DB_URL, "admin", "root");
    };
}
