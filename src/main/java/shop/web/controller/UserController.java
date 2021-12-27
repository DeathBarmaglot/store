package shop.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.service.UserService;


@Controller
public class UserController {

    @Autowired
    private final UserService userService;

    @RequestMapping(path = "/admin", method = RequestMethod.GET)
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.findAllUsers());
        return "admin";
    }

    public UserController(UserService userService) {
        this.userService = userService;
    }

   @DeleteMapping(path="{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        userService.removeUser(userId);
    }
}