package shop.dao.jdbc;

import shop.Food;
import shop.dao.FoodDao;
import shop.dao.jdbc.mapper.FoodMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDao implements FoodDao {
    private static final FoodMapper FOOD_MAPPER = new FoodMapper();
    private static final String NEW_SQL = "CREATE TABLE Goods (id SERIAL, name VARCHAR(100), price int, date DATE);";
    private static final String FIND_ALL_SQL = "SELECT id, name, price, date FROM goods;";
    private static final String ADD_SQL = "INSERT INTO goods (name, price, date)" +
            "VALUES (?, ?, ?, ?);";

    //+ "VALUES (10, 'cheese', 100, '2020-11-10')";
    public List<Food> findAll() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            List<Food> foods = new ArrayList<>();
            while (resultSet.next()) {
                Food food = FOOD_MAPPER.mapRow(resultSet);
                foods.add(food);
            }
            return foods;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void create(Food food) throws SQLException {
        Connection connection = getConnection();
        connection.createStatement().execute(NEW_SQL);
    }

    public void add(Food food) {
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_SQL + " ")) {
            preparedStatement.setString(1, food.getName());
            preparedStatement.setInt(2, food.getPrice());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(food.getDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Food> findByName(String name) {
        return null;
    }
    private Connection getConnection() throws SQLException {
        final String DB_URL = "jdbc:postgresql://192.168.31.249:5432/store";
        return DriverManager.getConnection(DB_URL, "admin", "root");
    }
}
