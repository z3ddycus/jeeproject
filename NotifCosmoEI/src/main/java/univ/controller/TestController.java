package univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import univ.domain.Component;
import univ.domain.Effect;
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
        
        List<Effect> ea = new LinkedList<>();
        ea.add(new Effect("irrite"));
        ea.add(new Effect("pique les yeux"));
        Component a = new Component("acide");
        a.setEffects(ea);
        
        List<Effect> eb = new LinkedList<>();
        eb.add(new Effect("rend soul"));
        eb.add(new Effect("se dilue bien"));
        Component b = new Component("acide alcooholique", a);
        b.setEffects(eb);
        
        List<Effect> ec = new LinkedList<>();
        ec.add(new Effect("nettoie"));
        ec.add(new Effect("balaie mais rarement"));
        Component c = new Component("base");
        c.setEffects(ec);
        
        List<Effect> ed = new LinkedList<>();
        ed.add(new Effect("pique les doigts"));
        ed.add(new Effect("chatouille les narines"));
        Component d = new Component("acide sulfurique", a);
        d.setEffects(ed);
        
        List<Effect> ee = new LinkedList<>();
        ee.add(new Effect("brule"));
        ee.add(new Effect("pique les pieds"));
        Component e = new Component("acide méthanol", a);
        e.setEffects(ee);
        
        List<Effect> ef = new LinkedList<>();
        ef.add(new Effect("mauvais gout"));
        ef.add(new Effect("ne pas boire"));
        Component f = new Component("acide alcooholique modifié", b);
        f.setEffects(ef);
        System.out.println("Ajout dans f :" + f.getEffects().size());
        List<Effect> eg = new LinkedList<>();
        eg.add(new Effect("basiquement mauvais"));
        eg.add(new Effect("modification alien"));
        Component g = new Component("base modifié", b);
        g.setEffects(eg);
        
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
    @RequestMapping("/modify")
    public String modify() {
        Product p = new Product();
        p.setId(1);
        p.setName("plopiplop");
        productService.update(p);
        return "redirect:/product/";
    }
}
