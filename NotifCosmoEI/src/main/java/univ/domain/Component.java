package univ.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * <b>Entité représentant un composant.</b>
 *
 * Il est identifié par un nom, sa composition, ses parents et ses enfants.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
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
     * Suppression des composants des produits.
     */
    @PreRemove
    private void deletingEntity() {
        for (Product p : products) {
            p.getComponents().remove(this);
        }
    }

    // REQUESTS

    /**
     * Retourne l'Id du composant.
     *
     * @return L'identifiant du composant.
     */
    public long getId() {
        return id;
    }

    /**
     * Retourne le nom du composant.
     *
     * @return Le nom du composant.
     */
    public String getName() {
        return name;
    }

    /**
     *
     *
     * @return Le parent du composant.
     */
    public Component getParent() {
        return parent;
    }

    /**
     * Retourne les effets concernant directement le composant.
     *
     * @return Les effets concernant le composant.
     */
    public Set<Effect> getEffects() {
        return Collections.synchronizedSet(effects);
    }

    /**
     * Retourne les produits contenant le composant.
     *
     * @return Les produits dans lesquels le composant est contenu.
     */
    public Set<Product> getProducts() {
        return Collections.synchronizedSet(products);
    }

    /**
     * Retourne la liste des parents en partant de la racine.
     *
     * @return La liste des parents.
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
     * Retourne les enfants ayant comme parent ce composant.
     *
     * @return Les enfants de ce composant.
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
     * Retourne la liste des effets concernés et hérités.
     *
     * @return Les effets cocnernés et hérités.
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
     * Met à jour l'Id du composant.
     *
     * @param id Le nouvel Id.
     */
    private void setId(long id) {
        this.id = id;
    }

    /**
     * Met à jour le nom du composant
     *
     * @param name Le nouveau Nom.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Met à jour le parent du composant.
     *
     * @param parent Le nouveau composant parent.
     */
    public void setParent(Component parent) {
        this.parent = parent;
    }

    /**
     * Met à jour les effets du composant.
     *
     * @param effects Les nouveaux effets du composant.
     */
    private void setEffects(Collection<Effect> effects) {
        this.effects.clear();
        this.effects.addAll(effects);
    }

    /**
     * Met à jour les produits du composant.
     *
     * @param products Les nouveaux produits.
     */
    private void setProducts(Collection<Product> products) {
        this.products.clear();
        this.products.addAll(products);
    }

    /**
     * Met à jour les enfants du composant.
     *
     * @param children les nouveaux enfants.
     */
    private void setChildren(Set<Component> children) {
        this.children.clear();
        this.children.addAll(children);
    }
}
