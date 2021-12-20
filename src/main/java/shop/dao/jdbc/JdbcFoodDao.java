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
    private static final String FIND_FOOD_SQL = "SELECT name FROM foods WHERE name =?;";
    private static final String FIND_FOOD_ID_SQL = "SELECT id, name, comment, price, date FROM foods WHERE id =?;";
    private static final String ADD_FOOD_SQL = "INSERT INTO foods (id, name, comment, price, date) VALUES (?, ?, ?, ?, ?);";
    private static final String EDIT_FOOD_SQL = "UPDATE foods (name, comment, price, date, id) VALUES (?, ?, ?, ?, ?);";
    private static final String REMOVE_FOOD_SQL = "DELETE FROM foods WHERE id =?;";

    @Override
    public void addFood(Food food) {
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(ADD_FOOD_SQL + " ")) {
            preparedStatement.setLong(1, food.getId());
            preparedStatement.setString(2, food.getName());
            preparedStatement.setString(3, food.getComment());
            preparedStatement.setInt(4, food.getPrice());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(food.getDate()));
//            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("addFood" + resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editFood(Food food) {

        //        for (Food foo: foods) {
//            if (food.getId() == foo.getId()){
//                foo.setName(food.getName());
//                foo.setPrice(food.getPrice());
//            }
//        }
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(EDIT_FOOD_SQL + " ")) {
            preparedStatement.setLong(1, food.getId());
            preparedStatement.setString(2, food.getName());
            preparedStatement.setString(3, food.getComment());
            preparedStatement.setInt(4, food.getPrice());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(food.getDate()));
//            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("editFood" + resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeFood(long id) {
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(REMOVE_FOOD_SQL + " ")) {
            preparedStatement.setLong(1, id);
//            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("removeFood" + resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isFoodExists(Food food) {
        try {
            Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(FIND_FOOD_ID_SQL);
            preparedStatement.setString(1, food.getName());
//            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("findFood" + resultSet.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Food> findAllFood() {
        List<Food> foods = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(ALL_FOODS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                foods.add(FOOD_MAPPER.mapRow((resultSet)));
            }
            //       System.out.println(foods);
            return foods;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public List<Food> findFoodByName(String name) {
        try {
            Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(FIND_FOOD_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);

            List<Food> foods = new ArrayList<>();
            while (resultSet.next()) {
                Food food = FOOD_MAPPER.mapRow(resultSet);
                foods.add(food);
            }

            return foods;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Food not found");
        }
        return null;
    }

    @Override
    public Food findFoodById(long id) {
        Food food = null;
        try {
            Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(FIND_FOOD_ID_SQL);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                food = FOOD_MAPPER.mapRow(resultSet);
//                System.out.println(food);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return food;
    }
    
    private Connection getConnection() throws SQLException {
        final String DB_URL = "jdbc:postgresql://192.168.31.249:5432/store";
        return DriverManager.getConnection(DB_URL, "admin", "root");
    }
}
