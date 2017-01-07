package univ.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import univ.domain.User;
import univ.repository.UserRepository;

@Service
public class CurrentContext {

    @Autowired
    private UserRepository userRepository;

    Model initModel(Model model) {
        User currentUser = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            currentUser = userRepository.findByMail(((UserDetails)principal).getUsername());
        } else {
            currentUser = userRepository.findByMail(principal.toString());
        }
        model.addAttribute("user", currentUser);
        model.addAttribute("isLogged", currentUser != null);
        return model;
    }
}
