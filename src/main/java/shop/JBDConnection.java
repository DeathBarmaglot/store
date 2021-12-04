package shop;

import java.sql.*;
import java.util.HashMap;

public class JBDConnection {

    static final String DB_URL = "jdbc:postgresql://192.168.31.249:5432/store";
    static final String USER = "admin";
    static final String PASS = "root";
    static HashMap<String, String> array = new HashMap<>();

    public static void main(String[] argv) throws SQLException {

        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return;
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");
        Connection connection;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
            Statement statement = connection.createStatement();
            System.out.println("Reading foods records...");
            System.out.printf("%-30.30s  %-30.30s%n", "Name", "Price");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM goods");
            while (resultSet.next()) {
                array.put(resultSet.getString("name"), resultSet.getString("price"));
                System.out.printf("%-30.30s  %-30.30s%n",
                        resultSet.getString("name"),
                        resultSet.getString("price"));
                System.out.println(getArr());
            }
        } else {
            System.out.println("Failed to make connection to database");
        }
    }

    public static HashMap getArr() {
        return array;
    }

}

