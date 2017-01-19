package univ.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * <b>Entité représentant un rôle d'utilisateur.</b>
 *
 * Il est identifié par un nom (EX : USER/ADMIN).
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
@Entity
public class Role implements Serializable{

    // ATTRIBUTES

    /**
     * Un identifiant définie automatiquement.
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
     * Retourne l'Id du role.
     *
     * @return L'identifiant du role.
     */
    public Long getId() {
        return id;
    }

    /**
     * Retourne le nom du role.
     *
     * @return Le nom du role.
     */
    public String getName() {
        return name;
    }

    // METHODS

    /**
     * Met à jour l'Id du role.
     *
     * @param id Le nouvel Id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Met à jour le nom du role
     *
     * @param name Le nouveau Nom.
     */
    public void setName(String name) {
        this.name = name;
    }
}