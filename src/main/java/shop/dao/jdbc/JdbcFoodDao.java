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
    private static final String FIND_FOOD_SQL = "SELECT name FROM foods WHERE name =?";
    private static final String FIND_FOOD_ID_SQL = "SELECT id, name, comment, price, date FROM foods WHERE id =?;";
    private static final String ADD_FOOD_SQL = "INSERT INTO foods (name, comment, price, date, id) VALUES (?, ?, ?, ?, ?);";
    private static final String EDIT_FOOD_SQL = "UPDATE foods SET name =?, comment =?, price =?, date=? WHERE id =?";
    private static final String REMOVE_FOOD_SQL = "DELETE FROM foods WHERE id =?;";

    @Override
    public void addFood(Food food) {
        setProps(food, ADD_FOOD_SQL);
    }

    @Override
    public void editFood(Food food) {
        setProps(food, EDIT_FOOD_SQL);
    }

    @Override
    public List<Food> findAllFood() {
        return getProps(ALL_FOODS_SQL);
    }

    @Override
    public List<Food> findFoodByName(String name) {
        return getProps(FIND_FOOD_SQL + name);
    }

    @Override
    public Food findFoodById(long id) {
        return getPropsById(id);

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
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private Food getPropsById(long id) {
        try {
            Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(FIND_FOOD_ID_SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Food food = null;
            while (resultSet.next()) {
                food = FOOD_MAPPER.mapRow(resultSet);
                System.out.println("findFoodById " + food);
            }
            return food;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private Connection getConnection() throws SQLException {
        final String DB_URL = "jdbc:postgresql://192.168.31.249:5432/store";
        return DriverManager.getConnection(DB_URL, "admin", "root");
    }

    private void setProps(Food food, String sql) {
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql + " ")) {
            preparedStatement.setString(1, food.getName());
            preparedStatement.setString(2, food.getComment());
            preparedStatement.setInt(3, food.getPrice());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(food.getDate()));
            preparedStatement.setLong(5, food.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
