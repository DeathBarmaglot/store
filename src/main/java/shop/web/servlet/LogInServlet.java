package shop.web.servlet;

import shop.web.entity.User;
import shop.service.UserService;
import shop.web.utils.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class LogInServlet extends HttpServlet {
    private final UserService userService;
    private final List<String> userToken;
    PageGenerator pageGenerator = PageGenerator.instance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String page = pageGenerator.getPage("index.html");
        resp.getWriter().write(page);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = User.builder()
            .name(req.getParameter("name"))
            .email(req.getParameter("email"))
            .pwd(req.getParameter("pwd"))
            .build();

//        if (userService.isUserExists(user)) {
            String userToken = UUID.randomUUID().toString();
            Cookie cookie = new Cookie("userToken", userToken);
            resp.addCookie(cookie);
            resp.sendRedirect("/main");
//            System.out.println("Log post" + cookie + " " + userToken);
//        } else {
//            String error = "<div>Incorrect name or password</div>";
//            Map<String, Object> param = Map.of("error", error);
//            String page = pageGenerator.getPage("add.html", param);
//            resp.getWriter().write(page);
//        }
    }

     public LogInServlet (UserService userService, List<String> userToken) {
        this.userService = userService;
        this.userToken = userToken;
     }
}
