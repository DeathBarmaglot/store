//package shop.web.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import shop.service.UserService;
//
//import java.sql.SQLException;
//
//@Controller
//@RequestMapping("/")
//public class UserController {
//
//    @Autowired
//    public UserService userService;
//
//    @GetMapping("/")
//    public String index(){
//        return "index";
//    }
//
//    @GetMapping("/add")
//    public String add(){
//        return "add";
//    }
//
//    @GetMapping("/main")
//    public String getAllUsers(Model model){
//        model.addAttribute("users", userService.findAllUsers());
//        return "main";
//    }
//
//    @GetMapping("/user/{email}")
//    public String getByEmail(@PathVariable("email") String email, Model model) throws SQLException {
//        model.addAttribute("user", userService.findUserByEmail(email));
//        return "user";
//    }
//}
