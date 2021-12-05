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
            add(new Food("Meat", 24, 12));
            add(new Food("Milk", 33, 11));
            add(new Food("Tofu", 55, 10));
        }};

        req.setAttribute("foods", foods);
        getServletContext().getRequestDispatcher("/first-jsp.jsp").forward(req, resp);
    }
}
