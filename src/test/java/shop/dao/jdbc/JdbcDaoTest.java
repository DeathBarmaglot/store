// TODO 500 Food not found
// 200  System.out.println(foods);

//isFoodExists
//The column name date was not found in this ResultSet.
//
//System.out.println(resultSet);
//add System.out.println(resultSet);
//user login
// FoodService(FoodDao foodDao)
// List<Food> findFoodById(int id)
//System.out.println("addFood"+ resultSet);
//mapRow(ResultSet resultSet);



package shop.dao.jdbc;

import org.junit.jupiter.api.Test;
import shop.dao.FoodDao;
import shop.web.entity.Food;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JdbcDaoTest {
    @Test
    public void testDataNotNull() {
        JdbcFoodDao jdbcDao = new JdbcFoodDao();
        System.out.println(jdbcDao.findAllFood());
        List<Food> foods = jdbcDao.findAllFood();
        System.out.println(foods);
        assertFalse(foods.isEmpty());
        for (Food food : foods) {
//            assertNotEquals(0, food.getId());
            assertNotNull(food.getId());
            assertNotNull(food.getName());
            assertNotNull(food.getDate());
            assertNotNull(food.getComment());
            assertNotNull(food.getPrice());
        }
    }

    @Test
    public boolean isFoodExists(Food food){
        return false;
    }

    @Test
    public List<Food> findAllFood(){
        return null;
    }

    @Test
    public void addFood(Food food){

    }

    @Test
    public void editFood(Food food){

    }

    @Test
    public void removeFood(int id){

    }


    public List<Food> findFoodByName(String name) {
        return null;
    }


    public List<Food> findFoodById(int id) {
        return null;
    }

    //AddDoPostFood(name=banana, comment=null, price=1000, id=0, date=2021-12-17T00:25:02.027759600)
//        [Food(name=cheese, comment=null, price=100, id=10, date=2022-12-22T00:00), Food(name=orange, comment=null, price=2, id=0, date=2021-12-17T00:00)]

//    @Test
//    public void testDAO() {
//        List<Food> foods = jdbcDao.findAllFood();
//        assertFalse(foods.isEmpty());
//        System.out.println(foods);
//        for (Food food : foods) {
//            assertNotEquals(0, food.getId());
//            assertNotNull(food.getId());
//            assertNotNull(food.getDate());
//            assertNotNull(food.getName());
//            assertNotNull(food.getPrice());
//        }
//    }
}