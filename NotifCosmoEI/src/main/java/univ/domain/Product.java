package univ.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * <b>Entité représentant un produit.</b>
 *
 * Il est identifié par un nom et une composaition.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
@Entity
public class Product implements Comparable<Product>, Serializable {

    // ATTRIBUTES

    /**
     * L'id.
     */
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private long id;

    /**
     * Le nom.
     */
    @Column(unique = true)
    private String name;

    /**
     * Les composants associées.
     */
    @ManyToMany
    private Set<Component> components = new TreeSet<>();

    // REQUESTS

    /**
     * Retourne l'Id du produit.
     *
     * @return L'identifiant du produit.
     */
    public long getId() {
        return id;
    }

    /**
     * Retourne le nom du produit.
     *
     * @return Le nom du produit.
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne les composants concernant directement le produit.
     *
     * @return Les composants concernant le produit.
     */
    public Set<Component> getComponents() {
        return components;
    }

    /**
     * Retourne les effets concernant directement le produit.
     *
     * @return Les effets concernant le produit.
     */
    public Set<Effect> getEffects() {
        Set result = new HashSet();
        for (Component c : components) {
            result.addAll(c.getEffects());
        }
        return result;
    }

    @Override
    public int compareTo(Product o) {
        return name.compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return name == null ? 0 : name.hashCode();
    }

    // METHODS

    /**
     * Met à jour l'Id du produit.
     *
     * @param id Le nouvel Id.
     */
    private void setId(long id) {
        this.id = id;
    }

    /**
     * Met à jour le nom du produit
     *
     * @param name Le nouveau Nom.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Met à jour les composants du produit.
     *
     * @param components Les nouveaux composants.
     */
    public void setComponents(Collection<Component> components) {
        this.components.clear();
        this.components.addAll(components);
    }
}
