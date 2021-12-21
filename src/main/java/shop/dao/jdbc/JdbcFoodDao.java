package shop.dao.jdbc;

import shop.web.entity.Food;
import shop.dao.FoodDao;
import shop.dao.jdbc.mapper.FoodMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcFoodDao implements FoodDao {
    private static final FoodMapper FOOD_MAPPER = new FoodMapper();
    private static final String ALL_FOODS_SQL = "SELECT id, name, comment, price, date FROM foods;";
    private static final String ADD_FOOD_SQL = "INSERT INTO foods (name, comment, price, date, id) VALUES (?, ?, ?, ?, ?);";
    private static final String EDIT_FOOD_SQL = "UPDATE foods SET name =?, comment =?, price =?, date=? WHERE id =?";
    private static final String REMOVE_FOOD_SQL = "DELETE FROM foods WHERE id =?;";
    List<Food> foods = new ArrayList<>();

    @Override
    public void addFood(Food food) {
        promise(food, ADD_FOOD_SQL);
    }


    @Override
    public void editFood(Food food) {
        promise(food, EDIT_FOOD_SQL);
    }

    @Override
    public void removeFood(long id) {
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(REMOVE_FOOD_SQL + " ")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Food> findAllFood() {
        foods = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(ALL_FOODS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                foods.add(FOOD_MAPPER.mapRow((resultSet)));
            }
            return foods;
        } catch (SQLException e) {
            System.err.println("Products not found");
            e.printStackTrace();
        }
        return foods;
    }

    @Override
    public List<Food> findFoodByName(String name) {
        List<Food> result = null;
        for (Food food : foods) {
            if (food.getName().equals(name)) {
                result.add(food);
            }
        }
        return result;
    }

    @Override
    public boolean isFoodExists(long id) {
        for (Food food : foods) {
            if (food.getId() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Food findFoodById(long id) {
        for (Food food : foods) {
            if (food.getId() == id) {
                return food;
            }
        }
        throw new IllegalArgumentException();
    }

    private Connection getConnection() throws SQLException {
        final String DB_URL = "jdbc:postgresql://192.168.31.249:5432/store";
        return DriverManager.getConnection(DB_URL, "admin", "root");
    }

    private void promise(Food food, String sql) {
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql + " ")) {
            preparedStatement.setString(1, food.getName());
            preparedStatement.setString(2, food.getComment());
            preparedStatement.setInt(3, food.getPrice());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(food.getDate()));
            preparedStatement.setLong(5, food.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error 500");
            e.printStackTrace();
        }
    }
}
