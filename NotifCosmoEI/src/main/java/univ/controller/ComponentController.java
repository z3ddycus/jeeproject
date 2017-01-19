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

/**
 * <b>Contrôleur d'un composant.</b>
 *
 * Définit les actions à suivre selon la requête CRUD invoquée.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
@Controller
@RequestMapping("/component")
public class ComponentController {

    /**
     * Service de composant.
     */
    @Autowired
    private ComponentService componentService;
    /**
     * Service de rapport.
     */
    @Autowired
    private ReportService reportService;
    /**
     * Service d'effets.
     */
    @Autowired
    private EffectService effectService;

    /**
     * Lors d'un appel <i>POST</i>, définit la création d'un composant.
     * @param model Le modèle de la requête.
     * @param compForm Le composant.
     * @return La réponse de la requête.
     */
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String create(Model model, @ModelAttribute("newComponent") Component compForm) {
        // traitement
        Component c = componentService.create(compForm);

        return "redirect:/component/" + c.getId();
    }

    /**
     * Lors d'un appel <i>GET</i> simple, définit la réponse à envoyer.
     * @param model Le modèle de la requête.
     * @return La réponse de la requête.
     */
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String getAll(Model model) {
        // args views
        model.addAttribute("autocompleteValues", componentService.getAutocompleteValues());
        model.addAttribute("newComponent", new Component());
        model.addAttribute("components", componentService.getAll());
        return "allComponent";
    }
    /**
     * Lors d'un appel <i>GET</i> avec paramètre, définit la réponse à envoyer.
     * @param model Le modèle de la requête.
     * @param id L'identifiant du composant que nous cherchons.
     * @return La réponse de la requête.
     */
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

    /**
     * Lors d'un appel <i>POST</i> avec paramètre, définit la modification à
     * effectuer.
     *
     * @param model Le modèle de la requête.
     * @param id L'identifiant du composant que nous voulons modifier.
     * @param description La nouvelle description du composant.
     * @return Le retour de la requête.
     */
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
        }
        return "redirect:/component/" + id;
    }

    /**
     * Lors d'une requête <i>GET</i> sur la route /{Id}/rename, définit le nouveau
     * nom du composant.
     * @param model Le modèle de la requête.
     * @param id L'identifiant du composant à renommer.
     * @param name Le nouveau nom.
     * @return La réponse de la requête.
     */
    @RequestMapping(value="/{Id}/rename", method= RequestMethod.GET)
    public String rename(Model model, @PathVariable(value="Id") String id, @RequestParam("name") String name) {
    long longId = Long.parseLong(id);
    Component p = componentService.get(longId);
    p.setName(name);
    p=componentService.update(p);
    return "redirect:/component/" + id;
    }

    /**
     * Lors d'une requête <i>GET</i> sur la route /{Id}/delete, définit la
     * suppression du composant.
     * @param model Le modèle de la requête.
     * @param id L'identififant du composant à supprimer.
     * @return La réponse de la requête.
     */
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
