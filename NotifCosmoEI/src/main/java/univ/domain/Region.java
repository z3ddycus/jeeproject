package univ.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * <b>Entité représentant une région.</b>
 *
 * Elle est identifiée par un nom. Le principe de mettre ces informations en
 * base est de permettre au système d'évoluer sur le temps.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
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
     * Retourne l'Id de la région.
     *
     * @return L'identifiant de la région.
     */
    public Long getId() {
        return id;
    }

    /**
     * Retourne le nom de la région.
     *
     * @return Le nom de la région.
     */
    public String getName() {
        return name;
    }

    // METHODS

    /**
     * Met à jour l'Id de la région.
     *
     * @param id Le nouvel Id.
     */
    private void setId(Long id) {
        this.id = id;
    }

    /**
     * Met à jour le nom de la région
     *
     * @param name Le nouveau Nom.
     */
    public void setName(String name) {
        this.name = name;
    }
}