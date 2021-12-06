package shop.dao;

import shop.Food;

import java.sql.SQLException;
import java.util.List;

public interface DAO {
    List<Food> findAll() throws SQLException;
}
