package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAO;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class MyController {

    private final UserService userService;

    @Autowired
    public MyController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
	public String showAllUsers(Model model){

        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allUsers", allUsers);

        return "all-users";
	}

    @GetMapping(value = "/addNewUser")
    public String addNewUser(Model model){

        User user = new User();
        model.addAttribute("user", user);

        return "user-info";
    }

    @PostMapping(value = "/saveUser")
    public String saveUser(@ModelAttribute("user") User user){

        userService.saveUser(user);

        return "redirect:/";
    }

    @GetMapping(value = "/updateInfo")
    public String updateUser(@RequestParam("id") int id, Model model){

        User user = userService.getUser(id);
        model.addAttribute("user",user);

        return "user-info";
    }

    @PostMapping(value = "/deleteUser")
    public String deleteUser(@RequestParam("id") int id){

        userService.deleteUser(id);

        return "redirect:/";
    }
	
}