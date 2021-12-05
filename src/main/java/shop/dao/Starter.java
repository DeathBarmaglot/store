package shop.dao;

import shop.Food;
import shop.dao.jdbc.JdbcDao;

public class Starter {
    public static void main(String[] args) {
       JdbcDao jdbcDao = new JdbcDao();
        for (Food food : jdbcDao.findAll()) {
            System.out.println(food);
        }
    }
}
