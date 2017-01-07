package univ.domain;


import javax.persistence.*;
import java.util.List;

@Entity
public class Component {

    // ATTRIBUTES

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Effect> effects;

    @ManyToMany(mappedBy="components")
    private List<Product> products;

    @OneToOne
    private Component parent;

    // CONSTRUCTOR

    public Component() {
        this.parent = null;
    }

    public Component(String name, Component parent) {
        this.name = name;
        this.parent = parent;
    }
    public Component(String name) {
        this.name = name;
        this.parent = null;
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

    public List<Effect> getEffects() {
        return effects;
    }

    public void setEffects(List<Effect> effects) {
        this.effects = effects;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
