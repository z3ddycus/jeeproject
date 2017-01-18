package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import univ.domain.*;
import univ.repository.EffectRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Le service des effets indésirables.
 */
@Service
public class EffectService {

    // ATTRIBUTES

    /**
     * Le repository des effets.
     */
    @Autowired
    private EffectRepository effectRepository;

    /**
     * Le service des utilisateurs.
     */
    @Autowired
    private UserService userService;

    // REQUESTS

    /**
     * L'effets indésirables associés à l'id : id.
     */
    public Effect get(long id) {
        return effectRepository.findOne(id);
    }

    /**
     * La liste totale des effets.
     */
    public List<Effect> getAll() {
        return effectRepository.findAll();
    }

    /**
     * L'ensemble des effets dont l'utilisateur associé est de la region region.
     */
    public Set<Effect> getByRegion(Region region) {
        return effectRepository.findByRegion(region);
    }

    /**
     * L'ensemble de toutes les descriptions existantes.
     */
    public Set<String> getAllDescription() {
        return effectRepository.findAllDescription();
    }

    /**
     * L'ensemble des effets associés au produit product.
     */
    public Set<Effect> getByProduct(Product product) {
        Set<Effect> result = new HashSet<>();
        for (Component c : product.getComponents()) {
            result.addAll(effectRepository.findByComponent(c));
        }
        return result;
    }

    /**
     * L'ensemble des effets associés à l'utilisateur User.
     */
    public Set<Effect> getByUser(User user) {
        return effectRepository.findByUser(user);
    }

    // METHODS

    /**
     * Crée l'effet e et renvoit l'effet correspondant dans le repository.
     */
    public Effect create(Effect e) {
        if (e.getUser() == null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            e.setUser(userService.findByMail(((UserDetails)principal).getUsername()));
        }
        return effectRepository.save(e);
    }

    /**
     * Supprimer l'effet effect du repository.
     */
    public void delete(Effect effect) {
        effectRepository.delete(effect);
    }
}