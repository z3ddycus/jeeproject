package univ.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    // ATTRIBUTES

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToMany
    private List<Component> components;

    // CONSTRUCTOR

    protected Product() {}

    public Product(String name, List<Component> components) {
        this.name = name;
        this.components = new ArrayList<>(components);
    }
}
