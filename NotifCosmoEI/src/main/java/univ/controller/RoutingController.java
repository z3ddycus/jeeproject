package univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import univ.service.UserService;

@Controller
@RequestMapping("/")
public class RoutingController{
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/profil";
    }

    @GetMapping("/error")
    public String error(Model model) {
        return "error";
    }
}