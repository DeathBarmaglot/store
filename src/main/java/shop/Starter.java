package shop;

import shop.dao.jdbc.JdbcFoodDao;
import shop.dao.jdbc.JdbcUserDao;
import shop.service.FoodService;
import shop.service.UserService;
import shop.web.servlet.*;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.ArrayList;
import java.util.List;

public class Starter {
    public static void main(String[] args) throws Exception {

        JdbcFoodDao jdbcFoodDao = new JdbcFoodDao();
        FoodService foodService = new FoodService(jdbcFoodDao);

        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        FoodService jdbcService = new FoodService(jdbcFoodDao);
        MainPage mainPage = new MainPage(foodService);
        AddFoodServlet addFoodServlet = new AddFoodServlet(foodService);
        EditFoodServlet editFoodServlet = new EditFoodServlet(foodService);

        UserService userService = new UserService(jdbcUserDao);
        List<String> userToken = new ArrayList<>();

        LogInServlet logInServlet =  new LogInServlet(userService, userToken);

        ShowList showList = new ShowList(foodService);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler.addServlet(new ServletHolder(logInServlet), "/");
        contextHandler.addServlet(new ServletHolder(showList), "/list");
        contextHandler.addServlet(new ServletHolder(mainPage), "/main");
        contextHandler.addServlet(new ServletHolder(addFoodServlet), "/add");
        contextHandler.addServlet(new ServletHolder(addFoodServlet), "/product");
        contextHandler.addServlet(new ServletHolder(editFoodServlet), "/edit");
        //        RemoveFoodServlet removeFoodServlet = new RemoveFoodServlet(foodService);

        Server server = new Server(9999);
        server.setHandler(contextHandler);

        server.start();
    }
}
