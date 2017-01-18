package univ.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Une région.
 */
@Entity
public class Region implements Serializable {

    /**
     * L'id.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * Le nom.
     */
    @Column(unique=true)
    private String name;

    // REQUESTS

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
    private void setId(Long id) {
        this.id = id;
    }

    /**
     * Setter du nom.
     */
    public void setName(String name) {
        this.name = name;
    }
}