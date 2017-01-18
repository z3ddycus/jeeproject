package univ.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Les effets indésirables.
 */
@Entity
public class Effect implements Serializable, Comparable<Effect> {

    /**
     * L'id.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    /**
     * L'utilisateur déclarant.
     */
    @ManyToOne
    private User user;

    /**
     * La date de déclaration.
     */
    private Date date;

    /**
     * Le composant associé.
     */
    @ManyToOne
    private Component component;

    /**
     * La description de l'effet.
     */
    private String description;

    // REQUESTS

    /**
     * L'id.
     */
    public long getId() {
        return id;
    }

    /**
     * L'utilisateur déclarant.
     */
    public User getUser() {
        return user;
    }

    /**
     * La date de déclaration.
     */
    public Date getDate() {
        return date;
    }

    /**
     * La description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Le composant associé.
     */
    public Component getComponent() {
        return component;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Effect effect = (Effect) o;

        if (user != null ? !user.equals(effect.user) : effect.user != null) return false;
        if (date != null ? !date.equals(effect.date) : effect.date != null) return false;
        if (component != null ? !component.equals(effect.component) : effect.component != null) return false;
        return description != null ? description.equals(effect.description) : effect.description == null;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (component != null ? component.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Effect o) {
        int val = description.compareTo(o.getDescription());
        if (val != 0) return val;

        return date.compareTo(o.getDate());
    }

    // METHODS

    /**
     * Setter du component.
     */
    public void setComponent(Component component) {
        this.component = component;
    }

    /**
     * Setter de la description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter de la date.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Setter de l'utilisateur.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Setter de l'id.
     */
    private void setId(long id) {
        this.id = id;
    }
}
