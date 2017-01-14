package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.domain.Component;
import univ.repository.ComponentRepository;

import java.util.*;


@Service
public class ComponentService {

    @Autowired
    private ComponentRepository componentRepository;



    public List<Component> getAll() {
        List<Component> result = componentRepository.findAll();
        Collections.sort(result);
        return result;
    }

    public Map<String, Long> getAutocompleteValues() {
        Map<String, Long> result = new HashMap<>();
        for (Component c : getAll()) {
            result.put(c.getName(), c.getId());
        }
        return result;
    }

    public Component get(long id) {
        return componentRepository.findOne(id);
    }
    public Component get(String name) {
        return componentRepository.findByName(name);
    }



    public void delete(long e) {
        componentRepository.delete(e);
    }

    @Transactional
    public Component update(Component component) {
        Component c = componentRepository.findOne(component.getId());
        c.setName(component.getName());
        c.setParent(component.getParent());
        return componentRepository.save(c);
    }

    @Transactional
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
        newC.setEffects(component.getEffects());
        return componentRepository.save(component);
    }
}
