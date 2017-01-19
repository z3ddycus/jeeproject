package univ.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <b>Contrôleur de l'index.</b>
 *
 * Définit les actions à suivre selon la requête CRUD invoquée.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
@Controller
@RequestMapping("/")
public class RoutingController{
    /**
     * Définit l'index de l'application.
     * @param model Le modèle de la requête.
     * @return La réponse de la requete.
     */
    @GetMapping("/")
    public String index(Model model) {
        return "redirect:/profil";
    }

    /**
     * Définit la route d'erreur rencontrée.
     * @param model Le modèle de la requête.
     * @return La réponse de la requête.
     */
    @GetMapping("/error")
    public String error(Model model) {
        return "error";
    }
}