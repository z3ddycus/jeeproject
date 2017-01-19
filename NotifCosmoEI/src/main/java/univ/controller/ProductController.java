package univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import univ.domain.Component;
import univ.domain.Product;
import univ.service.ComponentService;
import univ.service.ProductService;
import univ.service.ReportService;

/**
 * <b>Contrôleur d'un produit.</b>
 *
 * Définit les actions à suivre selon la requête CRUD invoquée.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    /**
     * Service de produit.
     */
    @Autowired
    private ProductService productService;
    /**
     * Service de rapport.
     */
    @Autowired
    private ReportService reportService;
    /**
     * Service de composant.
     */
    @Autowired
    private ComponentService componentService;

    /**
     * Lors d'un appel <i>POST</i>, définit la création d'un produit.
     * @param model Le modèle de la requête.
     * @return La réponse de la requête.
     */
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("newProduct", new Product());
        model.addAttribute("autocompleteValues", productService.getAutocompleteMap());
        model.addAttribute("products", productService.getAll());
        return "allProduct";
    }

    /**
     * Lors d'une requête <i>GET</i> sur la route /{Id}/rename, définit le nouveau
     * nom du produit.
     * @param model Le modèle de la requête.
     * @param id L'identifiant du produit à renommer.
     * @param name Le nouveau nom.
     * @return La réponse de la requête.
     */
    @RequestMapping(value="/{Id}/rename", method= RequestMethod.GET)
    public String rename(Model model, @PathVariable(value="Id") String id, @RequestParam("name") String name) {
        long longId = Long.parseLong(id);
        Product p = productService.get(longId);
        p.setName(name);
        p=productService.update(p);
        return "redirect:/product/" + id;
    }

    /**
     * Lors d'une requête <i>GET</i> sur la route /{Id}/addComponent/{Id},
     * définit l'ajout de composant au produit fournis en tête de requête.
     * @param model Le modèle de la requête.
     * @param id L'identifiant du produit sur lequel nous ajoutons le composant.
     * @param idComp Le composant que nous ajoutons.
     * @return La réponse de la requête.
     */
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
    /**
     * Lors d'un appel <i>GET</i> avec paramètre, définit la réponse à envoyer.
     * @param model Le modèle de la requête.
     * @param id L'identifiant du produit que nous cherchons.
     * @return La réponse de la requête.
     */
    @RequestMapping(value="/{ID}", method= RequestMethod.GET)
    public String get(Model model, @PathVariable(value="ID") String id) {
        long longId = Long.parseLong(id);
        Product p = productService.get(longId);
        model.addAttribute("product", p);
        model.addAttribute("newComponent", new Component());
        model.addAttribute("components", componentService.getAll());
        model.addAttribute("autocompleteValues", componentService.getAutocompleteValues());
        model.addAttribute("reports", reportService.getReports(p));
        return "product";
    }
    /**
     * Lors d'une requête <i>GET</i> sur la route /{Id}/delete, définit la
     * suppression du produit.
     * @param model Le modèle de la requête.
     * @param id L'identififant du produit à supprimer.
     * @return La réponse de la requête.
     */
    @RequestMapping(value="/{ID}/delete", method= RequestMethod.GET)
    public String delete(Model model, @PathVariable(value="ID") String id) {
        try {
            long longId = Long.parseLong(id);
            productService.delete(longId);
        } catch (Exception ignored) {
        }
        return "redirect:/product/";
    }
    /**
     * Lors d'un appel <i>POST</i>, définit la création d'un produit.
     * @param model Le modèle de la requête.
     * @param prodForm Le produit.
     * @return La réponse de la requête.
     */
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String create(Model model, @ModelAttribute("newProduct") Product prodForm) {
        Product p = productService.create(prodForm);
        return "redirect:/product/" + p.getId();
    }
}
