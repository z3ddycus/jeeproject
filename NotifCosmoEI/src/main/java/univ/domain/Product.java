package univ.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

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
     * L'id.
     */
    public long getId() {
        return id;
    }

    /**
     * Le nom.
     */
    public String getName() {
        return name;
    }

    /**
     * Les composants associées.
     */
    public Set<Component> getComponents() {
        return components;
    }

    /**
     * Les effets associés.
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
     * Setter de l'id.
     */
    private void setId(long id) {
        this.id = id;
    }

    /**
     * Setter du nom.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter des components.
     */
    public void setComponents(Collection<Component> components) {
        this.components.clear();
        this.components.addAll(components);
    }
}
