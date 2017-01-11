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
import univ.domain.entity.User;
import univ.service.UserService;
import univ.service.WorkService;

@Controller
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private WorkService workService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("works", workService.getAll());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, Model model) {
        // TODO: 07/01/17 vérifier la validité du user si non renvoit vers registration
        userService.save(userForm);
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        return "login";
    }

    @RequestMapping(value="/user/{ID}", method= RequestMethod.GET)
    public String get(Model model, @PathVariable(value="ID") String id) {
        try {
            long longId = Long.parseLong(id);
            User user = userService.get(longId);
            if (user == null) {
                model.addAttribute("message", "L'utilisateur n'existe pas.");
                return "redirect:/error";
            }
            model.addAttribute("user", user);
            return "profil";
        } catch (Exception e) {
            model.addAttribute("message", "L'utilisateur n'existe pas.");
            return "redirect:/error";
        }
    }

    @RequestMapping(value= "/users", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("autocompleteValues", userService.getAutocompleteValues());
        model.addAttribute("users", userService.getAll());
        return "allUser";
    }

    @RequestMapping(value="/profil", method=RequestMethod.GET)
    public String profil(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByMail(((UserDetails)principal).getUsername());
        model.addAttribute("user", user);
        return "profil";

    }
}
