package univ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoutingController{

    @GetMapping("/")
    public String index() {
        return "index";
    }
}