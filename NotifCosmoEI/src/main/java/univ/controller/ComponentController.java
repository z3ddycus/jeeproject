package univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import univ.domain.Component;
import univ.domain.Effect;
import univ.domain.Product;
import univ.service.ComponentService;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Controller
@RequestMapping("/component")
public class ComponentController {
    @Autowired
    ComponentService componentService;

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String create(Model model, @ModelAttribute("newComponent") Component compForm) {
        Component c = componentService.create(compForm);
        return "redirect:/component/" + c.getId();
    }
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("newComponent", new Component());
        model.addAttribute("components", componentService.getAll());
        return "allComponent";
    }

    @RequestMapping(value="/{ID}", method= RequestMethod.GET)
    public String get(Model model, @PathVariable(value="ID") String id) {
        try {
            long longId = Long.parseLong(id);
            Component c = componentService.get(longId);
            if (c == null) {
                model.addAttribute("message", "Le composant n'existe pas.");
                return "redirect:/error";
            }
            List<Component> parents = c.getInheritanceList();
            Set<Effect> effects = c.getInheritanceEffects();
            Set<Product> products = new TreeSet<>(c.getProducts());
            for (Component comp : c.getInheritanceChildren()) {
                products.addAll(comp.getProducts());
            }
            model.addAttribute("products", products);
            model.addAttribute("component", c);
            model.addAttribute("inheritance", parents);
            model.addAttribute("effects", effects);
            return "component";
        } catch (Exception e) {
            return "redirect:/component/";
        }
    }
}
