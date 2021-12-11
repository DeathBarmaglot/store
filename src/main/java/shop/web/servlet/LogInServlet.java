package shop.web.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shop.web.util.PageGenerator;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("index.html");
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String pwd = req.getParameter("password");
        String userToken = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("userToken", userToken);
        resp.addCookie(cookie);
        resp.addCookie(new Cookie("language", "ua"));
        resp.sendRedirect("/");
        System.out.println(name+" "+pwd+" "+userToken);

    }
}
