package shop;

import shop.dao.jdbc.JdbcFoodDao;
import shop.dao.jdbc.JdbcUserDao;
import shop.service.FoodService;
import shop.service.SecurityService;
import shop.service.UserService;
import shop.web.servlet.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {
    public static void main(String[] args) throws Exception {
        FoodService foodService = new FoodService(new JdbcFoodDao());
        UserService userService = new UserService(new JdbcUserDao());
        SecurityService securityService = new SecurityService(userService, foodService);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler.addServlet(new ServletHolder(new LogInServlet(securityService)), "/");
        contextHandler.addServlet(new ServletHolder(new AdminServlet(userService)), "/admin");
        contextHandler.addServlet(new ServletHolder(new MainPage(foodService, userService)), "/main");
        contextHandler.addServlet(new ServletHolder(new AddFoodServlet(foodService)), "/add");
        contextHandler.addServlet(new ServletHolder(new EditFoodServlet(foodService)), "/edit");

        Server server = new Server(9999);
        server.setHandler(contextHandler);

        server.start();
    }
}
