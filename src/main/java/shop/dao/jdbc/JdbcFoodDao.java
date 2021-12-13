package shop.dao.jdbc;

import shop.web.entity.Food;
import shop.dao.FoodDao;
import shop.dao.jdbc.mapper.FoodMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcFoodDao implements FoodDao  {
    private static final FoodMapper FOOD_MAPPER = new FoodMapper();
    private static final String NEW_SQL = "CREATE TABLE Goods (id SERIAL, name VARCHAR(100), price int, date DATE);";
    private static final String FOODS_SQL = "SELECT id, name, price, date FROM goods;";
    private static final String FIND_FOOD_SQL = "SELECT name, email, pwd FROM users WHERE name =?;";
    private static final String ADD_FOOD_SQL = "INSERT INTO goods (name, price, date) VALUES (?, ?, ?, ?);";

    @Override
    public void addFood(Food food) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_FOOD_SQL + " ")) {
            preparedStatement.setString(1, food.getName());
            preparedStatement.setInt(2, food.getPrice());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(food.getDate()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editFood(Food food) {
    }

    @Override
    public void removeFood(int id) throws SQLException {

    }

    @Override
   public boolean isFoodExists(Food food) {

       try{ Connection connection = getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement(FIND_FOOD_SQL);
           preparedStatement.setString(1, food.getName());
           ResultSet resultSet = preparedStatement.executeQuery();
//TODO

//           List<Food> foods = new ArrayList<>();
//           System.out.println("Exist "+ foods+" "+resultSet);

       } catch (SQLException e) {
           e.printStackTrace();
       }

       return false;
   }



    @Override
    public List<Food> findAllFood() {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FOODS_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
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



    @Override
    public List<Food> findFoodByName(String name) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_FOOD_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
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


    private Connection getConnection() throws SQLException {
        final String DB_URL = "jdbc:postgresql://192.168.31.249:5432/store";
        return DriverManager.getConnection(DB_URL, "admin", "root");
    }
}

