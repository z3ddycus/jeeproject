package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.domain.Component;
import univ.repository.ComponentRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Le service associé aux composants.
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
     */
    public List<Component> getAll() {
        List<Component> result = componentRepository.findAll();
        Collections.sort(result);
        return result;
    }

    /**
     * La map d'association des clés primaires des composants.
     * Elle est associe la propriété name à la propriété id. (Ces données sont bijectives)
     */
    public Map<String, Long> getAutocompleteValues() {
        Map<String, Long> result = new HashMap<>();
        for (Component c : getAll()) {
            result.put(c.getName(), c.getId());
        }
        return result;
    }

    /**
     * Le composant associé à l'id : id.
     * ou null si aucun n'existe.
     */
    public Component get(long id) {
        return componentRepository.findOne(id);
    }

    /**
     * Le composant associé à la propriété name valant name.
     * ou null si aucun n'existe.
     */
    public Component get(String name) {
        return componentRepository.findByName(name);
    }

    // METHODS

    /**
     * Supprime le composant d'id e.
     */
    public void delete(long e) {
        componentRepository.delete(e);
    }

    /**
     * Met à jour component dans le repository.
     * @Pre : component.getId() != null
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
     * Crée un composant component dans le repository.
     * Renvoit le component lié au repo ou null si une erreur survient.
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
}
