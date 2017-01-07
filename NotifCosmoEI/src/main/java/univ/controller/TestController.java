package univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import univ.domain.Component;
import univ.domain.Product;
import univ.service.ProductService;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/initProduct")
    public String initProduct() {
        List<Component> l = new LinkedList<>();
        l.add(new Component("a"));
        l.add(new Component("b"));
        l.add(new Component("c"));
        Product p = new Product("WithComponent");
        p.setComponents(l);
        productService.create(p);
        productService.create(new Product("WithSameComponent", l));
        productService.create(new Product("Cacahuete2"));
        productService.create(new Product("Cacahuete3"));
        productService.create(new Product("Cacahuete4"));
        return "redirect:/product/";
    }
    @RequestMapping("/modify")
    public String modify() {
        Product p = new Product();
        p.setId(1);
        p.setName("plopiplop");
        productService.update(p);
        return "redirect:/product/";
    }
}
