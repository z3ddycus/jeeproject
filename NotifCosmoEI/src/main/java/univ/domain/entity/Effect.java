package univ.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Effect implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) private long id;
    @ManyToOne private User user;
    private Date date;
    @ManyToOne private Component component;

    private String description;


    public Effect() {}
    public Effect(String description) {
        this.description = description;
    }


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
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
}
