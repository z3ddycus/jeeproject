package univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import univ.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CurrentContext context;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String getAll(Model model) {
        context.initModel(model);
        model.addAttribute("products", productService.getAll());
        return "allProduct";
    }

    @RequestMapping(value="/{ID}", method= RequestMethod.GET)
    public String getAll(Model model, @PathVariable(value="ID") String id) {
        context.initModel(model);
        try {
            long longId = Long.parseLong(id);
            model.addAttribute("product", productService.get(longId));
            return "product";
        } catch (Exception e) {
            return "redirect:/product/";
        }
    }

    @RequestMapping(value="/{ID}/delete", method= RequestMethod.GET)
    public String delete(Model model, @PathVariable(value="ID") String id) {
        context.initModel(model);
        try {
            long longId = Long.parseLong(id);
            productService.delete(longId);
            return "redirect:/product/";
        } catch (Exception e) {
            return "redirect:/product/";
        }
    }
}
