package univ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RoutingController{
    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/profil";
    }

    @GetMapping("/error")
    public String error(Model model) {
        return "error";
    }
}