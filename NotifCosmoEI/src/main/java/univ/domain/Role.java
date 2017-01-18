package univ.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Un role.
 */
@Entity
public class Role implements Serializable{

    // ATTRIBUTES

    /**
     * L'id.
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * Le nom.
     */
    @Column(unique=true)
    private String name;

    // ATTRIBUTES

    /**
     * L'id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Le nom.
     */
    public String getName() {
        return name;
    }

    // METHODS

    /**
     * Setter de l'id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Setter du nom.
     */
    public void setName(String name) {
        this.name = name;
    }
}