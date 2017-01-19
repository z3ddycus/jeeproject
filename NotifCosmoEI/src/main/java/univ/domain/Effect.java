package univ.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <b>Entité représentant un effet indésirable.</b>
 *
 * Il est identifié par une description, une date de déclaration, un composant
 * auquel il est associé et le déclarant.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
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
     * Retourne l'Id de l'effet indésirable.
     *
     * @return L'identifiant de l'effet indésirable.
     */
    public long getId() {
        return id;
    }

    /**
     * Retourne l'utilisateur déclarant.
     *
     * @return L'identifiant du déclarant
     */
    public User getUser() {
        return user;
    }

    /**
     * Retourne la date de déclaration.
     *
     * @return La date de déclaration.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Retourne la description.
     *
     * @return La description de l'effet indésirable.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Retourne le composant associé.
     *
     * @return Le composant de l'effet indésirable.
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
     * Met à jour le composant de l'effet indésirable.
     *
     * @param component Le nouveau composant.
     */
    public void setComponent(Component component) {
        this.component = component;
    }

    /**
     * Met à jour la description de l'effet indésirable.
     *
     * @param description La description de l'effet indésirable.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Met à jour la date de l'effet indésirable.
     *
     * @param date La nouvelle date.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Met à jour l'utilisateur déclarant de l'effet indésirable.
     *
     * @@param user Le nouveau utilisateur déclarant.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Met à jour l'Id de l'effet indésirable.
     *
     * @param id Le nouvel Id.
     */
    private void setId(long id) {
        this.id = id;
    }
}
