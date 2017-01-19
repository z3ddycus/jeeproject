package univ.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <b>Entité représentant une profession.</b>
 *
 * Il est identifié par un nom et son poids.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
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
     * Retourne l'Id de la profession.
     *
     * @return L'identifiant de la profession.
     */
    public Long getId() {
        return id;
    }

    /**
     * Retourne le nom de la profession.
     *
     * @return Le nom de la profession.
     */
    public String getName() {
        return name;
    }

    /**
     * Retourne le poids de la profession.
     *
     * @return Le poids de la profession.
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
     * Met à jour l'Id de la profession.
     *
     * @param id Le nouvel Id.
     */
    private void setId(Long id) {
        this.id = id;
    }

    /**
     * Met à jour le nouveau nom de la profession.
     *
     * @param name Le nouveau nom.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Met à jour le nouveau poids de la profession.
     *
     * @param weight Le nouveau poids.
     */
    public void setWeight(float weight) {
        this.weight = weight;
    }


}