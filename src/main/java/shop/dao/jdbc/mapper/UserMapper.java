package shop.dao.jdbc.mapper;

import shop.web.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {
    public User mapRow(ResultSet resultSet) throws SQLException {
       String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String role = resultSet.getString("role");
        boolean auth = resultSet.getBoolean("auth");

        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(role)
                .auth(auth)
                .build();
    }
}
