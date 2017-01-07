package univ.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    // ATTRIBUTES

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Component> components;

    // CONSTRUCTOR

    public Product(String name, List<Component> components) {
        this.name = name;
        this.components = new ArrayList<>(components);
    }
    public Product(String name) {
        this.name = name;
    }
    public Product() {
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

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }
}
