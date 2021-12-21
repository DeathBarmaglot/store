package shop.web.servlet;

import shop.service.SecurityService;
import shop.web.entity.User;
import shop.service.UserService;
import shop.web.utils.PageGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LogInServlet extends HttpServlet {
    private final UserService userService;
    private SecurityService securityService;
        private final List<String> userToken;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PageGenerator pageGenerator = PageGenerator.instance();
        String page = pageGenerator.getPage("index.html");
        resp.getWriter().write(page);


//        String token = getToken(req);
//        boolean isAuth = securityService.isLoggedIn(token);

//        if (!isAuth) {


//            User user = User.builder()
//                    .email(req.getParameter("email"))
//                    .pwd(req.getParameter("password"))
//                    .build();
//            try {
//                token = securityService.signIn(user);
//                Cookie cookie = new Cookie("user-token", token);
//                resp.addCookie(cookie);
//                resp.sendRedirect("/");
//
//            } catch (Exception e) {
////                String error = "Invalid email or password!";
////                String page = pageGenerator("login.html", error);
////                resp.getWriter().write(page);
//            }
//        } else {
//            resp.sendRedirect("/");
//        }
    }

    private String getToken(HttpServletRequest req) {

        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        User user = User.builder()
            .name(req.getParameter("name"))
            .email(req.getParameter("email"))
            .pwd(req.getParameter("pwd"))
            .build();
        System.out.println(user);
        resp.sendRedirect("/main");

//        if (userService.isUserExists(user.getEmail()) {
//            String userToken = UUID.randomUUID().toString();
//            Cookie cookie = new Cookie("userToken", userToken);
//            resp.addCookie(cookie);
//            resp.sendRedirect("/main");
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
