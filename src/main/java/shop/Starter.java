package shop;

import shop.dao.jdbc.JdbcDao;
import shop.service.FoodService;
import shop.web.servlet.AddFoodServlet;
import shop.web.servlet.EditFoodServlet;
import shop.web.servlet.LogInServlet;
import shop.web.servlet.ShowAllRequestServlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {
    public static void main(String[] args) throws Exception {

        JdbcDao jdbcDao = new JdbcDao();
        FoodService foodService = new FoodService(jdbcDao);
        ShowAllRequestServlet showAllRequestServlet = new ShowAllRequestServlet(foodService);
        AddFoodServlet addFoodServlet = new AddFoodServlet(foodService);
        EditFoodServlet editFoodServlet = new EditFoodServlet(foodService);

        ServletContextHandler contextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler.addServlet(new ServletHolder(new LogInServlet()), "/login");
        contextHandler.addServlet(new ServletHolder(showAllRequestServlet), "/");
        contextHandler.addServlet(new ServletHolder(addFoodServlet), "/add");
        contextHandler.addServlet(new ServletHolder(editFoodServlet), "/edit");

        Server server = new Server(9999);
        server.setHandler(contextHandler);

        server.start();
    }
}
