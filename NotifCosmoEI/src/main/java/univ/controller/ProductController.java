package univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import univ.domain.entity.Component;
import univ.domain.entity.Product;
import univ.service.ComponentService;
import univ.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ComponentService componentService;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("newProduct", new Product());
        model.addAttribute("autocompleteValues", productService.getAutocompleteMap());
        model.addAttribute("products", productService.getAll());
        return "allProduct";
    }

    @RequestMapping(value="/{Id}/rename", method= RequestMethod.GET)
    public String rename(Model model, @PathVariable(value="Id") String id, @RequestParam("name") String name) {
        long longId = Long.parseLong(id);
        Product p = productService.get(longId);
        p.setName(name);
        p=productService.update(p);
        return "redirect:/product/" + id;
    }

    @RequestMapping(value="/{Id}/addComponent/{IdComponent}", method= RequestMethod.GET)
    public String addComponent(Model model, @PathVariable(value="Id") String id, @PathVariable(value="IdComponent") String idComp) {
        long longId = Long.parseLong(id);
        Product p = productService.get(longId);
        long longIdComp = Long.parseLong(idComp);
        Component c = componentService.get(longIdComp);
        p.getComponents().add(c);
        productService.update(p);
        return "redirect:/product/" + id;
    }


    @RequestMapping(value="/{ID}", method= RequestMethod.GET)
    public String get(Model model, @PathVariable(value="ID") String id) {
        try {
            long longId = Long.parseLong(id);
            Product p = productService.get(longId);
            model.addAttribute("product", p);
            model.addAttribute("newComponent", new Component());
            model.addAttribute("components", componentService.getAll());
            model.addAttribute("autocompleteValues", componentService.getAutocompleteValues());
            model.addAttribute("effects", p.getEffects());
            return "product";
        } catch (Exception e) {
            return "redirect:/product/";
        }
    }

    @RequestMapping(value="/{ID}/delete", method= RequestMethod.GET)
    public String delete(Model model, @PathVariable(value="ID") String id) {
        try {
            long longId = Long.parseLong(id);
            productService.delete(longId);
        } catch (Exception ignored) {
        }
        return "redirect:/product/";
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String create(Model model, @ModelAttribute("newProduct") Product prodForm) {
        Product p = productService.create(prodForm);
        return "redirect:/product/" + p.getId();
    }
}
