package shop.dao.jdbc;

//AUTO inc
//SELECT id, name, comment, price, date FROM foods;

import shop.web.entity.Food;
import shop.dao.FoodDao;
import shop.dao.jdbc.mapper.FoodMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class JdbcFoodDao implements FoodDao {
    private static final FoodMapper FOOD_MAPPER = new FoodMapper();
    private static final String NEW_FOOD_SQL = "CREATE TABLE foods (id SERIAL, name VARCHAR(100), comment VARCHAR(100), price int, date DATE);";
    private static final String ALL_FOODS_SQL = "SELECT id, name, comment, price, date FROM foods;";
    private static final String FIND_FOOD_SQL = "SELECT name FROM foods WHERE name =?;";
    private static final String FIND_FOOD_ID_SQL = "SELECT id FROM foods WHERE id =?;";
    private static final String ADD_FOOD_SQL = "INSERT INTO foods (id, name, comment, price, date) VALUES (?, ?, ?, ?, ?);";
    private static final String EDIT_FOOD_SQL = "UPDATE foods (name, comment, price, date, id) VALUES (?, ?, ?, ?, ?);";
    private static final String REMOVE_FOOD_SQL = "DELETE FROM foods (id) VALUES (?);";

//    List<Food> foods = new ArrayList<>();
//
//    {
//        Food food = Food.builder().id(1).name("apple").price(10).date(LocalDateTime.now()).build();
//        foods.add(food);
//    }

    @Override
    public void createFood() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(NEW_FOOD_SQL)) {
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
         //   System.out.println(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addFood(Food food) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_FOOD_SQL + " ")) {
            preparedStatement.setInt(1, food.getId());
            preparedStatement.setString(2, food.getName());
            preparedStatement.setString(3, food.getComment());
            preparedStatement.setInt(4, food.getPrice());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(food.getDate()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("addFood"+ resultSet);

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
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(EDIT_FOOD_SQL + " ")) {
                preparedStatement.setInt(1, food.getId());
            preparedStatement.setString(2, food.getName());
            preparedStatement.setString(3, food.getComment());
            preparedStatement.setInt(4, food.getPrice());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(food.getDate()));
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
     //        System.out.println(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeFood(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_FOOD_SQL + " ")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isFoodExists(Food food) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_FOOD_ID_SQL);
            preparedStatement.setString(1, food.getName());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Food> findAllFood() {
        List<Food> foods = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ALL_FOODS_SQL);
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
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_FOOD_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            List<Food> foods = new ArrayList<>();
            while (resultSet.next()) {
                Food food = FOOD_MAPPER.mapRow(resultSet);
                foods.add(food);
            }
//System.out.println(foods);
            return foods;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Food not found");
        }
        return null;
    }

    @Override
    public List<Food> findFoodById(int id) {
        List<Food> foods = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_FOOD_ID_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                foods.add(FOOD_MAPPER.mapRow((resultSet)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
//        System.out.println(foods);
        return foods;
    }

    private Connection getConnection() throws SQLException {
        final String DB_URL = "jdbc:postgresql://192.168.31.249:5432/store";
        return DriverManager.getConnection(DB_URL, "admin", "root");
    }
}
