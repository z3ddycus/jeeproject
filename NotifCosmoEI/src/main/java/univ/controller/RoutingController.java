package univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import univ.domain.User;
import univ.service.UserService;

@Controller
@RequestMapping("/")
public class RoutingController{
    @Autowired
    UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", (user == null) ? "tout le monde" : user.getMail());
        return "index";
    }
}