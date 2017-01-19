package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import univ.domain.Effect;
import univ.domain.Region;
import univ.domain.User;
import univ.repository.EffectRepository;

import java.util.List;
import java.util.Set;


/**
 * <b>Service d'effets indésirables.</b>
 * Fonctionnalités de CRUD des effets indésirables utilisés par l'application.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
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
     * L'effet indésirable associé à l'id : id.
     * @param id L'id de l'effet
     * @return L'effet indésirable possédant cet id.
     */
    public Effect get(long id) {
        return effectRepository.findOne(id);
    }

    /**
     * La liste totale des effets.
     * @return La liste de tous les effets.
     */
    public List<Effect> getAll() {
        return effectRepository.findAll();
    }

    /**
     * L'ensemble des effets dont l'utilisateur associé est de la region region.
     * @param region La région
     * @return L'ensemble de tous les effets de la region
     */
    public Set<Effect> getByRegion(Region region) {
        return effectRepository.findByRegion(region);
    }

    /**
     * L'ensemble de toutes les descriptions existantes.
     * @return La liste de toutes les descriptions.
     */
    public Set<String> getAllDescription() {
        return effectRepository.findAllDescription();
    }

    /**
     * L'ensemble des effets associés à l'utilisateur User.
     * @param user L'utilisateur
     * @return L'ensemble des effets indésirables liés à l'utilisateur.
     */
    public Set<Effect> getByUser(User user) {
        return effectRepository.findByUser(user);
    }

    // METHODS

    /**
     * Crée l'effet e et renvoit l'effet correspondant dans le repository.
     * @param e L'effet
     * @return l'effet créé
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
     * @param effect l'effet à supprimer
     */
    public void delete(Effect effect) {
        effectRepository.delete(effect);
    }
}