package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.domain.Component;
import univ.repository.ComponentRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    public void delete(Component e) {
        componentRepository.delete(e);
    }

    public Component create(Component product) {
        Component p = componentRepository.findByName(product.getName());
        if (p != null) {
            return p;
        }
        return componentRepository.save(product);
    }
}
