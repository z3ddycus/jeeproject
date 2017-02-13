package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.domain.Component;
import univ.domain.Effect;
import univ.domain.Region;
import univ.repository.ComponentRepository;

import java.util.*;


/**
 * <b>Service de composants.</b>
 * Fonctionnalités de CRUD des composants utilisés par l'application.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
@Service
public class ComponentService {

    // ATTRIBUTES

    /**
     * Le repository des composants.
     */
    @Autowired
    private ComponentRepository componentRepository;


    // REQUESTS

    /**
     * La liste de tous les composants triés selon l'ordre alphabétique.
     * @return La liste de tous les composants triés par ordre alphabétique.
     */
    public List<Component> getAll() {
        List<Component> result = componentRepository.findAll();
        Collections.sort(result);
        return result;
    }

    /**
     * L'association nom à id.
     * @return Une map avec pour clé le nom du composant et comme valeur associé l'id du composant.
     */
    public Map<String, Long> getAutocompleteValues() {
        Map<String, Long> result = new HashMap<>();
        for (Component c : getAll()) {
            result.put(c.getName(), c.getId());
        }
        return result;
    }

    /**
     * Le composant associé à l'argument.
     * @param id l'id associé.
     * @return le composant possédant comme champ id, id. null sinon.
     */
    public Component get(long id) {
        return componentRepository.findOne(id);
    }

    /**
     * Le composant associé à l'argument.
     * @param name le nom associé.
     * @return le composant possédant comme champ name, name. null sinon.
     */
    public Component get(String name) {
        return componentRepository.findByName(name);
    }

    // METHODS

    /**
     * Supprime un composant.
     * @param e l'id du composant a supprimé.
     */
    public void delete(long e) {
        componentRepository.delete(e);
    }

    /**
     * Met à jour un composant.
     * @param component Le composant a mettre un jour
     * @return Le composant mis à jour.
     */
    public Component update(Component component) {
        Component c = componentRepository.findOne(component.getId());
        if (c == null) {
            return null;
        }
        c.setName(component.getName());
        c.setParent(component.getParent());
        return componentRepository.save(c);
    }

    /**
     * Crée un nouveau composant sur la base de component.
     * @param component Le composant à créer
     * @return Le composant créé.
     */
    public Component create(Component component) {
        if (component.getName() == null) {
            return null;
        }
        Component c = componentRepository.findOne(component.getId());
        if (c != null) {
            return c;
        } else if (component.getName() != null){
            c = componentRepository.findByName(component.getName());
            if (c != null) {
                return c;
            }
        }
        Component newC = new Component();
        newC.setName(component.getName());
        newC.setParent(component.getParent());
        return componentRepository.save(component);
    }

    public List<Component> getAllByRegion(Region r) {
        List<Component> result = new LinkedList<>();
        List<Component> components = componentRepository.findAll();
        for (Component c : components) {
            Set<Effect> effects = c.getInheritanceEffects();
            Iterator<Effect> it = effects.iterator();
            boolean stopLoop = false;
            while(!stopLoop && it.hasNext()) {
                Effect e = it.next();
                if (r == e.getUser().getRegion()) {
                    stopLoop = true;
                    result.add(c);
                }
            }
        }
        Collections.sort(result);
        return result;
    }
}
