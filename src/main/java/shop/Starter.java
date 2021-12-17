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


public class Starter {
    public static void main(String[] args) throws Exception {
        FoodService foodService = new FoodService(new JdbcFoodDao());
        UserService userService = new UserService(new JdbcUserDao());

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler.addServlet(new ServletHolder(new LogInServlet(userService, new ArrayList<>())), "/");
        contextHandler.addServlet(new ServletHolder(new RemoveServlet(foodService)), "/remove");
        contextHandler.addServlet(new ServletHolder(new MainPage(foodService, userService)), "/main");
        contextHandler.addServlet(new ServletHolder(new AddFoodServlet(foodService)), "/add");
        contextHandler.addServlet(new ServletHolder(new EditFoodServlet(foodService)), "/edit");

        Server server = new Server(9999);
        server.setHandler(contextHandler);

        server.start();
    }
}
