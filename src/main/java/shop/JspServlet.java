package shop;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/temp-serv")
public class JspServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Food> foods = new ArrayList<Food>() {{
            add(new Food("Meat", 24,"2022-12-22", 12));
            add(new Food("Milk", 33, "2021-12-22", 11));
            add(new Food("Tofu", 55, "2020-11-20", 10));
        }};

        req.setAttribute("foods", foods);
        getServletContext().getRequestDispatcher("/first-jsp.jsp").forward(req, resp);
    }
}
