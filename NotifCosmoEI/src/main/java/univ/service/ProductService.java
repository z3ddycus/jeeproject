package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.domain.Component;
import univ.domain.Product;
import univ.repository.ProductRepository;

import java.util.*;


/**
 * <b>Service de produits.</b>
 * Fonctionnalités de CRUD des produits utilisés par l'application.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
@Service
public class ProductService {

    // ATTRIBUTES

    /**
     * Le repository des produits.
     */
    @Autowired
    private ProductRepository productRepository;

    /**
     * Le service des composants.
     */
    @Autowired
    private ComponentService componentService;


    // REQUESTS

    /**
     * La liste de tous les produits triés par ordre alphabétique.
     * @return La liste de tous les produits
     */
    public List<Product> getAll() {
        List<Product> result = productRepository.findAll();
        Collections.sort(result);
        return result;
    }

    /**
     * Le produit associé au nom name ou null si non existant.
     * @param name Le nom
     * @return Le produit associé au nom.
     */
    public Product get(String name) {
        return productRepository.findByName(name);
    }

    /**
     * Le produit associé à l'id id ou null si non existant.
     * @param id L'id
     * @return Le produit associé à l'id.
     */
    public Product get(long id) {
        return productRepository.findOne(id);
    }

    /**
     * La map bijective du nom vers l'id de tous les produits.
     * @return Une map associé le champ nom à l'id.
     */
    public Map<String, Long> getAutocompleteMap() {
        Map<String, Long> result = new HashMap<>();
        for (Product p : getAll()) {
            result.put(p.getName(), p.getId());
        }
        return result;
    }

    // METHODS

    /**
     * Crée le produit product et renvoit le product associé dans le repository.
     * @param product le produit à créer.
     * @return le produit créé.
     */
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

    /**
     * Met à jour le product possédant l'id product.getId() dans le repository.
     * @param product le produit à mettre à jour
     * @return le produit mis à jour
     */
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

    /**
     * Supprime le product associé à l'id id dans le repository.
     * @param id L'id du produit à supprimer.
     */
    public void delete(long id) {
        productRepository.delete(id);
    }
}
