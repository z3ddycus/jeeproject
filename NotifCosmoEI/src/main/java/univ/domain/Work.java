package univ.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Un travail.
 */
@Entity
public class Work implements Serializable{

    // ATTRIBUTES

    /**
     * L'id.
     */
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * Le nom.
     */
    @Column(unique=true)
    private String name;

    /**
     * Le poids.
     */
    private float weight;

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

    /**
     * Le poids.
     */
    public float getWeight() {
        return weight;
    }

    @Override
    public String toString() {
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

    /**
     * Setter du poids.
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }


}