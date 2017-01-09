package univ.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Product implements Comparable<Product>, Serializable {

    // ATTRIBUTES

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private Set<Component> components;

    // CONSTRUCTOR

    public Product(String name, Collection<Component> components) {
        this(name);
        this.components.addAll(components);
    }
    public Product(String name) {
        this();
        this.name = name;
    }
    public Product() {
        this.components = new TreeSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Component> getComponents() {
        return components;
    }

    public void setComponents(Collection<Component> components) {
        this.components.clear();
        this.components.addAll(components);
    }
    public Set<Effect> getEffects() {
        Set result = new TreeSet();
        for (Component c : components) {
            result.addAll(c.getEffects());
        }
        return result;
    }
    @Override
    public int compareTo(Product o) {
        return name.compareTo(o.getName());
    }
}
