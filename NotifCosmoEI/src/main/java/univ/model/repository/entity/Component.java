package univ.model.repository.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Component {

    // ATTRIBUTES

    @Id
    @GeneratedValue
    private long id;

    private String name;

    @OneToOne
    private Component parent;

    // CONSTRUCTOR

    protected Component() {}

    public Component(String name, Component parent) {
        this.name = name;
        this.parent = parent;
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
}
