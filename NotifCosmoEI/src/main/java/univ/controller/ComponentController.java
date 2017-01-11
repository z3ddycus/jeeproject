package univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import univ.domain.EffectsResume;
import univ.domain.entity.Component;
import univ.domain.entity.Product;
import univ.service.ComponentService;

import java.util.*;

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
        model.addAttribute("autocompleteValues", componentService.getAutocompleteValues());
        model.addAttribute("newComponent", new Component());
        model.addAttribute("components", componentService.getAll());
        return "allComponent";
    }



    @RequestMapping(value="/{ID}", method= RequestMethod.GET)
    public String get(Model model, @PathVariable(value="ID") String id) {
        //try {
            long longId = Long.parseLong(id);
            Component c = componentService.get(longId);
            if (c == null) {
                model.addAttribute("message", "Le composant n'existe pas.");
                return "redirect:/error";
            }
            List<Component> parents = c.getInheritanceList();
            List<EffectsResume.InfoEffect> effects = new EffectsResume(c.getInheritanceEffects()).toList();
            Collections.sort(effects);

            Set<Product> products = new TreeSet<>(c.getProducts());
            for (Component comp : c.getInheritanceChildren()) {
                products.addAll(comp.getProducts());
            }
            model.addAttribute("products", products);
            model.addAttribute("component", c);
            model.addAttribute("inheritance", parents);
            model.addAttribute("effects", effects);
            return "component";
        /*} catch (Exception e) {
            System.out.println("oups");
            return "redirect:/component/";
        }*/
    }


    @RequestMapping(value="/{Id}/rename", method= RequestMethod.GET)
    public String rename(Model model, @PathVariable(value="Id") String id, @RequestParam("name") String name) {
        long longId = Long.parseLong(id);
        Component p = componentService.get(longId);
        p.setName(name);
        p=componentService.update(p);
        return "redirect:/component/" + id;
    }

    @RequestMapping(value="/{ID}/delete", method= RequestMethod.GET)
    public String delete(Model model, @PathVariable(value="ID") String id) {
        try {
            long longId = Long.parseLong(id);
            componentService.delete(longId);
        } catch (Exception ignored) {
        }
        return "redirect:/component/";
    }
}
