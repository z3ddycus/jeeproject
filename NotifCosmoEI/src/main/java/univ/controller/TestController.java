package univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import univ.domain.entity.Component;
import univ.domain.entity.Effect;
import univ.domain.entity.Product;
import univ.domain.entity.User;
import univ.service.ComponentService;
import univ.service.EffectService;
import univ.service.ProductService;
import univ.service.UserService;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ComponentService componentService;
    @Autowired
    private UserService userService;
    @Autowired
    private EffectService effectService;

    @RequestMapping("/initProduct")
    public String initProduct() {
        new Component("acide");
        Component a = componentService.create(new Component("acide"));
        Component b = componentService.create(new Component("acide alcooholique", a));
        Component c = componentService.create(new Component("base"));
        Component d = componentService.create(new Component("acide sulfurique", a));
        Component e = componentService.create(new Component("acide méthanol", a));
        Component f = componentService.create(new Component("acide alcoolhol ++", b));
        Component g = componentService.create(new Component("super base", c));

        List<Component> l = new LinkedList<>();
        l.add(a);
        l.add(b);
        l.add(c);
        productService.create(new Product("Basique", l));

        List<Component> l2 = new LinkedList<>();
        l2.add(e);
        l2.add(f);
        l2.add(g);
        productService.create(new Product("Basique 2", l2));

        List<Component> l3 = new LinkedList<>();
        l3.add(a);
        l3.add(b);
        l3.add(c);
        productService.create(new Product("Same as basique", l3));

        List<Component> l4 = new LinkedList<>();
        l4.add(d);
        l4.add(d);
        productService.create(new Product("Double composant", l4));

        productService.create(new Product("Empty"));

        return "redirect:/product/";
    }


    @RequestMapping("/initEffect")
    public String initEffect() {
        Component a = componentService.create(new Component("effectFull"));
        Effect e = new Effect();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByMail(((UserDetails)principal).getUsername());
        e.setUser(user);
        e.setDate(new Date());
        e.setDescription("mon effet un peu long mais il faut bien pour le test");
        e.setComponent(a);
        effectService.create(e);
        return initEffect2();
    }

    @RequestMapping("/initEffect2")
    public String initEffect2() {
        Component c = componentService.get("effectFull");
        Effect e = new Effect();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByMail(((UserDetails)principal).getUsername());
        e.setUser(user);
        e.setDate(new Date());
        e.setDescription("mon effet2 un peu long mais il faut bien pour le test");
        e.setComponent(c);
        effectService.create(e);
        return "redirect:/component/" + c.getId();
    }
}
