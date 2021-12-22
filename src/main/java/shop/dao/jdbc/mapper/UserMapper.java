package shop.dao.jdbc.mapper;

import shop.web.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class UserMapper {
    public User mapRow(ResultSet resultSet) throws SQLException {
       String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String pwd = resultSet.getString("pwd");
        Timestamp date = resultSet.getTimestamp("date");
        return User.builder()
                .name(name)
                .email(email)
                .pwd(pwd)
                .date(date.toLocalDateTime().now())
                .build();
    }
}
