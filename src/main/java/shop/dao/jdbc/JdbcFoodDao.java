package shop.dao.jdbc;

import shop.web.entity.Food;
import shop.dao.FoodDao;
import shop.dao.jdbc.mapper.FoodMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcFoodDao implements FoodDao {
    private static final FoodMapper FOOD_MAPPER = new FoodMapper();
    private static final String ALL_FOODS_SQL = "SELECT id, name, comment, price, email, date FROM foods;";
    private static final String ADD_FOOD_SQL = "INSERT INTO foods (name, comment, price, date, email, id) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String EDIT_FOOD_SQL = "UPDATE foods SET name =?, comment =?, price =?, date=?, email=? WHERE id =?";
    private static final String REMOVE_FOOD_SQL = "DELETE FROM foods WHERE id =?;";
    private static final String FIND_FOOD_ID_SQL = "SELECT id FROM foods WHERE id =?;";

    @Override
    public void addFood(Food food) {
        getProps(food, ADD_FOOD_SQL, food.getEmail());
    }

    @Override
    public void editFood(Food food) {
        getProps(food, EDIT_FOOD_SQL, food.getEmail());
    }

    @Override
    public void removeFood(long id) {
        getProps(id, REMOVE_FOOD_SQL);
    }

    @Override
    public Food findFoodById(long id) {
        return getProps(id, FIND_FOOD_ID_SQL);
    }

    @Override
    public List<Food> findAllFood() {
        return getProps(ALL_FOODS_SQL);
    }

    @Override
    public boolean isFoodExists(long id) {
        for (Food food : findAllFood()) {
            if (food.getId() == id) {
                return true;
            }
        }
        return false;
    }
//TODO ADMIN
    public Food findFoodById(long id, String sql) {
        return getProps(id, sql);
    }

    private Connection getConnection() throws SQLException {
        final String DB_URL = "jdbc:postgresql://192.168.31.249:5432/store";
        return DriverManager.getConnection(DB_URL, "admin", "root");
    }

    private Food getProps(long id, String sql) {
        Food food = null;
        try {
            Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                food = FOOD_MAPPER.mapRow(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return food;


    }


    private List<Food> getProps(String sql) {

        List<Food> foods = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                foods.add(FOOD_MAPPER.mapRow((resultSet)));
            }
            return foods;
        } catch (SQLException e) {
            System.err.println("Products not found");
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private void getProps(Food food, String sql, String email) {
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql + " ")) {
            preparedStatement.setString(1, food.getName());
            preparedStatement.setString(2, food.getComment());
            preparedStatement.setInt(3, food.getPrice());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(food.getDate()));

            preparedStatement.setString(5, email);
            preparedStatement.setLong(6, food.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error 500");
            e.printStackTrace();
        }
    }
}
