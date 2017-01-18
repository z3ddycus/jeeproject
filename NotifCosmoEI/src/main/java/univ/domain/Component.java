package univ.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Un composant.
 */
@Entity
public class Component implements Comparable<Component>, Serializable{

    // ATTRIBUTES

    /**
     * Un identifiant définie automatiquement.
     */
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private long id;

    /**
     * Le nom du composant.
     */
    @Column(unique = true)
    private String name;

    /**
     * Le type parent du composant.
     */
    @ManyToOne
    private Component parent;

    /**
     * Les effets indésirables.
     */
    @OneToMany(mappedBy="component", cascade={CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Effect> effects = new TreeSet<>();

    /**
     * Les produits l'utilisant.
     */
    @ManyToMany(mappedBy="components")
    private Set<Product> products = new TreeSet<>();

    /**
     * Les composants héritants.
     */
    @OneToMany(mappedBy="parent")
    private Set<Component> children = new TreeSet<>();


    // PREACTION

    /**
     * Supprime les composants des produits.
     */
    @PreRemove
    private void deletingEntity() {
        for (Product p : products) {
            p.getComponents().remove(this);
        }
    }

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
     * Le parent
     */
    public Component getParent() {
        return parent;
    }

    /**
     * Les effets concernant directement le composant.
     */
    public Set<Effect> getEffects() {
        return Collections.synchronizedSet(effects);
    }

    /**
     * Les produits contenant le composant.
     */
    public Set<Product> getProducts() {
        return Collections.synchronizedSet(products);
    }

    /**
     * La liste des parents en partant de la racine.
     */
    public List<Component> getInheritanceList() {
        LinkedList<Component> queue = new LinkedList<>();
        Component current = this;
        while(current != null) {
            queue.addFirst(current);
            current = current.parent;
        }
        return queue;
    }

    /**
     * Les enfants ayant comme parent ce composant.
     */
    public Set<Component> getChildren() {
        return Collections.unmodifiableSet(children);
    }

    /**
     * La liste de tous les enfants récursivement.
     */
    public SortedSet<Component> getInheritanceChildren() {
        SortedSet<Component> children = new TreeSet<>();
        for (Component c : this.children) {
            children.add(c);
            children.addAll(c.getChildren());
        }
        return Collections.unmodifiableSortedSet(children);
    }

    /**
     * La liste des effets concernées et hérités.
     */
    public SortedSet<Effect> getInheritanceEffects() {
        SortedSet<Effect> result = new TreeSet<>();
        for (Component comp : getInheritanceList()) {
            result.addAll(comp.getEffects());
        }
        return Collections.unmodifiableSortedSet(result);
    }

    @Override
    public int compareTo(Component o) {
        return name.compareTo(o.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return name != null ? name.equals(component.name) : component.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }


    // METHODS

    /**
     * Remplace l'id.
     */
    private void setId(long id) {
        this.id = id;
    }

    /**
     * Remplace le nom
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Remplace le parent
     */
    public void setParent(Component parent) {
        this.parent = parent;
    }

    /**
     * Remplace les effets.
     */
    private void setEffects(Collection<Effect> effects) {
        this.effects.clear();
        this.effects.addAll(effects);
    }

    /**
     * Remplace les produits
     */
    private void setProducts(Collection<Product> products) {
        this.products.clear();
        this.products.addAll(products);
    }

    /**
     * Remplace les enfants
     */
    private void setChildren(Set<Component> children) {
        this.children.clear();
        this.children.addAll(children);
    }


}
