package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.domain.Component;
import univ.domain.Product;
import univ.repository.ProductRepository;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ComponentService componentService;


    // REQUESTS

    public List<Product> getAll() {
        List<Product> result = productRepository.findAll();
        Collections.sort(result);
        return result;
    }

    public Product get(String name) {
        return productRepository.findByName(name);
    }

    public Product get(long id) {
        return productRepository.findOne(id);
    }

    public Map<String, Long> getAutocompleteMap() {
        Map<String, Long> result = new HashMap<>();
        for (Product p : getAll()) {
            result.put(p.getName(), p.getId());
        }
        return result;
    }

    // COMMANDS
    @Transactional
    public Product create(Product product) {
        Product p = productRepository.findOne(product.getId());
        if (p != null) {
            return p;
        } else {
            p = productRepository.findByName(product.getName());
            if (p != null) {
                return p;
            }
        }
        Product newP = new Product();
        newP.setName(product.getName());
        Set<Component> components = new HashSet<>();
        for (Component c : product.getComponents()) {
            Component newC = componentService.get(c.getId());
            components.add(newC);
        }
        newP.setComponents(components);
        return productRepository.save(newP);
    }

    public Product update(Product product) {
        Product newP = productRepository.findOne(product.getId());
        newP.setName(product.getName());
        Set<Component> components = new HashSet<>();
        for (Component c : product.getComponents()) {
            Component newC = componentService.get(c.getId());
            components.add(newC);
        }
        newP.setComponents(components);
        return productRepository.save(newP);
    }

    public void delete(Product p) {
        productRepository.delete(p);
    }

    public void delete(long id) {
        productRepository.delete(id);
    }
}
