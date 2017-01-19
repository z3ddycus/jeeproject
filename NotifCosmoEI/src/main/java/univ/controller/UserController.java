package univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import univ.domain.Effect;
import univ.domain.User;
import univ.repository.RoleRepository;
import univ.service.EffectService;
import univ.service.RegionService;
import univ.service.UserService;
import univ.service.WorkService;

/**
 * <b>Contrôleur d'un utilisateur.</b>
 *
 * Définit les actions à suivre selon la requête CRUD invoquée.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
@Controller
@RequestMapping("/")
public class UserController {
    /**
     * Service d'utilisateurs.
     */
    @Autowired
    private UserService userService;
    /**
     * Service de professions.
     */
    @Autowired
    private WorkService workService;
    /**
     * Service de région.
     */
    @Autowired
    private RegionService regionService;
    /**
     * Service de rôle.
     */
    @Autowired
    private RoleRepository roleRepository;
    /**
     * Service d'effets indésirables.
     */
    @Autowired
    private EffectService effectService;

    /**
     * Lors d'une requête <i>GET</i> sur la route /registration, permet d'accéder
     * à la page d'inscription.
     * @param model Le modèle de la requete.
     * @return La réponse de la requête.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("works", workService.getAll());
        model.addAttribute("regions", regionService.getAll());
        return "registration";
    }
    /**
     * Lors d'une requête <i>POST</i> sur la route /registration, permet de définir
     * la soumission du formulaire d'inscription.
     * @param userForm La soumission des données de l'utilisateur.
     * @param model Le modèle de la requete.
     * @return La réponse de la requête.
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, Model model) {
        if (userService.findByMail(userForm.getMail()) != null) {
            return "redirect:/registration";
        }
        userForm.setRole(roleRepository.findOneByName("USER"));
        userService.save(userForm);
        return "redirect:/";
    }
    /**
     * Lors d'une requête <i>GET</i> sur la route /login, permet d'accéder
     * à la page de connexion.
     * @param model Le modèle de la requete.
     * @param error Le retour d'erreur à indiquer.
     * @param logout L'état actuel.
     * @return La réponse de la requête.
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        return "login";
    }
    /**
     * Lors d'un appel <i>GET</i> avec paramètre, indique des détails sur
     * l'utilisateur demandé.
     * @param model Le modèle de la requête.
     * @param id L'identifiant de l'utilisateur que nous cherchons.
     * @return La réponse de la requête.
     */
    @RequestMapping(value="/user/{ID}", method= RequestMethod.GET)
    public String get(Model model, @PathVariable(value="ID") String id) {
        long longId = Long.parseLong(id);
        User user = userService.get(longId);
        if (user == null) {
            model.addAttribute("message", "L'utilisateur n'existe pas.");
            return "redirect:/error";
        }
        model.addAttribute("user", user);
        model.addAttribute("effects", effectService.getByUser(user));
        return "profil";
    }

    /**
     * Lors d'une requête <i>GET</i> sur la route /user/{idUser}/deleteEffect/{idEffect},
     * définit la suppression d'un effet.
     * @param model Le modèle de la requête.
     * @param idUser L'utilisateur concerné.
     * @param idEffect L'effet à supprimer.
     * @return Le retour de la requête.
     */
    @RequestMapping(value="/user/{idUser}/deleteEffect/{idEffect}", method=RequestMethod.GET)
    public String deleteEffect(Model model, @PathVariable(value="idUser") String idUser, @PathVariable(value="idEffect") String idEffect ) {
        long longIdUser = Long.parseLong(idUser);
        User user = userService.get(longIdUser);
        if (user == null) {
            model.addAttribute("message", "L'utilisateur n'existe pas.");
            return "redirect:/error";
        }
        long longIdEffet = Long.parseLong(idEffect);
        Effect effect = effectService.get(longIdEffet);
        if (!effect.getUser().equals(user)){
            model.addAttribute("message", "L'effect n'appartient pas à cet utilisateur");
            return "redirect:/error";
        }
        effectService.delete(effect);
        return "redirect:/user/" + idUser;
    }

    /**
     * Lors d'une requête <i>GET</i> sur la route /users, permet d'obtenir
     * l'intégralité des utilisateurs du système.
     * @param model Le modèle de la requete.
     * @return La réponse de la requête.
     */
    @RequestMapping(value= "/users", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("autocompleteValues", userService.getAutocompleteValues());
        model.addAttribute("users", userService.getAll());
        return "allUser";
    }
    /**
     * Lors d'une requête <i>GET</i> sur la route /profil, permet d'obtenir
     * l'intégralité des utilisateurs des informations liées à l'utilisateur
     * courrant.
     * @param model Le modèle de la requete.
     * @return La réponse de la requête.
     */
    @RequestMapping(value="/profil", method=RequestMethod.GET)
    public String profil(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByMail(((UserDetails)principal).getUsername());
        model.addAttribute("user", user);
        return "profil";

    }
}
