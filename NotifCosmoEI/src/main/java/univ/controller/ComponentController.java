package univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import univ.domain.Component;
import univ.domain.Effect;
import univ.domain.Product;
import univ.model.Report;
import univ.service.ComponentService;
import univ.service.EffectService;
import univ.service.ReportService;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Controller
@RequestMapping("/component")
public class ComponentController {


    @Autowired
    private ComponentService componentService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private EffectService effectService;

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String create(Model model, @ModelAttribute("newComponent") Component compForm) {
        // traitement
        Component c = componentService.create(compForm);

        return "redirect:/component/" + c.getId();
    }



    @RequestMapping(value="/", method= RequestMethod.GET)
    public String getAll(Model model) {
        // args views
        model.addAttribute("autocompleteValues", componentService.getAutocompleteValues());
        model.addAttribute("newComponent", new Component());
        model.addAttribute("components", componentService.getAll());
        return "allComponent";
    }



    @RequestMapping(value="/{ID}", method= RequestMethod.GET)
    public String get(Model model, @PathVariable(value="ID") String id) {
            long longId = Long.parseLong(id);
            Component c = componentService.get(longId);
            if (c == null) {
                model.addAttribute("message", "Le composant n'existe pas.");
                return "redirect:/error";
            }

            List<Component> parents = c.getInheritanceList();
            Set<Report> reports = new TreeSet<>(reportService.getReports(c));
            Set<Product> products = new TreeSet<>(c.getProducts());
            for (Component comp : c.getInheritanceChildren()) {
                products.addAll(comp.getProducts());
            }
            Set<String> autocomplete = effectService.getAllDescription();
            model.addAttribute("autocompleteValues", autocomplete);
            model.addAttribute("products", products);
            model.addAttribute("component", c);
            model.addAttribute("inheritance", parents);
            model.addAttribute("reports", reports);

            return "component";
    }

    @RequestMapping(value="/{Id}/addEffect", method= RequestMethod.POST)
    public String addEffect(Model model, @PathVariable(value="Id") String id, @RequestParam(value="description") String description) {
        long longId = Long.parseLong(id);
        Effect effect = new Effect();
        Component c = componentService.get(longId);
        if (c != null) {
            effect.setDate(new Date());
            effect.setDescription(description);
            effect.setComponent(c);
            effectService.create(effect);
            System.out.println("*" + description + "*");
        }
        return "redirect:/component/" + id;
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
