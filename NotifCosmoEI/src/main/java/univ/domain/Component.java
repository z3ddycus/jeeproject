package univ.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Component implements Comparable<Component>, Serializable{

    // ATTRIBUTES

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(cascade = {CascadeType.ALL})
    private Set<Effect> effects;

    @ManyToMany(mappedBy="components")
    private Set<Product> products;

    @OneToMany(mappedBy="parent", cascade = {CascadeType.ALL})
    private Set<Component> children;

    @ManyToOne
    private Component parent;

    // CONSTRUCTOR

    public Component() {
        children = new TreeSet<>();
        products = new TreeSet<>();
        effects = new TreeSet<>();
    }

    public Component(String name, Component parent) {
        this(name);
        this.parent = parent;
    }
    public Component(String name) {
        this();
        this.name = name;
    }


    // GETTER

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Component getParent() {
        return parent;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(Component parent) {
        this.parent = parent;
    }

    public Set<Effect> getEffects() {
        return effects;
    }

    public void setEffects(Collection<Effect> effects) {
        this.effects.clear();
        this.effects.addAll(effects);
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products.clear();
        this.products.addAll(products);
    }

    public List<Component> getInheritanceList() {
        LinkedList<Component> queue = new LinkedList<>();
        Component current = this;
        while(current != null) {
            queue.addFirst(current);
            current = current.parent;
        }
        return queue;
    }

    public Set<Component> getChildren() {
        return children;
    }
    public Set<Component> getInheritanceChildren() {
        Set<Component> children = new HashSet<>();
        for (Component c : this.children) {
            children.add(c);
            children.addAll(c.getChildren());
        }
        return children;
    }

    public Set<Effect> getInheritanceEffects() {
        Set<Effect> result = new TreeSet<>();
        for (Component comp : getInheritanceList()) {
            result.addAll(comp.getEffects());
        }
        return result;
    }

    public void setChildren(Set<Component> children) {
        this.children = children;
    }

    @Override
    public int compareTo(Component o) {
        return name.compareTo(o.getName());
    }
}
