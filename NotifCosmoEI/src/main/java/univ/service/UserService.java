package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.domain.User;
import univ.repository.RoleRepository;
import univ.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <b>Service des utilisateurs.</b>
 * Fonctionnalités de CRUD des composants utilisés par l'application.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
@Service
public class UserService {

    // ATTRIBUTES

    /**
     * Le repository des utilisateurs.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Le repository des roles.
     */
    @Autowired
    private RoleRepository roleRepository;

    // REQUESTS

    /**
     * La liste des tous les utilisateurs.
     * @return La liste de tous les utilisateurs.
     */
    public List<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * La liste de tous les "nom + prénom" associé à l'id.
     * @return Une map avec pour clé "nom + ' ' + prenom" et valeur id.
     */
    public Map<String, Long> getAutocompleteValues() {
        Map<String, Long> result = new HashMap<>();
        for (User user : getAll()) {
            result.put(user.getFirstName() + " " + user.getLastName(), user.getId());
        }
        return result;
    }

    /**
     * L'utilisateur possédant l'id id, ou null si aucun.
     * @param id l'id de l'utilisateur
     * @return L'utilisateur associé à l'id, null si non existant.
     */
    public User get(long id) {
        return userRepository.findOne(id);
    }

    /**
     * L'utilisateur associé au mail mail.
     * @param mail Le mail utilisateur associé.
     * @return L'utilisateur associé au mail, null si non existant.
     */
    public User findByMail(String mail) {
        return userRepository.findByMail(mail);
    }


    // METHODS

    /**
     * Sauvegarde l'utilisateur dans le repository si aucun role n'est associé USER est associé par défaut.
     * @param user l'utilisateur à sauvegarder
     */
    public void save(User user) {
        if (user.getRole() == null) {
            user.setRole(roleRepository.findOneByName("USER"));
        }
        userRepository.save(user);
    }
}