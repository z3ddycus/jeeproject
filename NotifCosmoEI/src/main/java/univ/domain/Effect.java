package univ.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Effect implements Comparable<Effect>, Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) private long id;
    @ManyToOne private User user;
    @CreatedDate private Date date;
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

    @Override
    public int compareTo(Effect o) {
        return description.compareTo(o.getDescription());
    }
}
