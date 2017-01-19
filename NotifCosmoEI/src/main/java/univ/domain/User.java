package univ.domain;

import javax.persistence.*;
import java.io.Serializable;


/**
 * <b>Entité représentant un utilisateur.</b>
 *
 * Il est identifié par un nom, un prénom, un mail, une région, un rôle et un
 * grade.
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
@Entity
@Table(name = "user_id")
public class User implements Serializable {

    // ATTRIBUTES

    /**
     * L'id.
     */
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * Le mail.
     */
    @Column(unique=true)
    private String mail;

    /**
     * Le prénom.
     */
    private String firstName;

    /**
     * Le nom.
     */
    private String lastName;

    /**
     * Le mot de passe.
     */
    private String password;

    /**
     * La région.
     */
    @ManyToOne
    private Region region;

    /**
     * Le role.
     */
    @ManyToOne
    private Role role;

    /**
     * Le travail.
     */
    @ManyToOne
    private Work work;

    // REQUESTS

    /**
     * Retourne la région de l'utilisateur.
     *
     * @return La région de l'utilisateur.
     */
    public Region getRegion() {
        return region;
    }

    /**
     * Retourne le mail de l'utilisateur.
     *
     * @return Le mail de l'utilisateur.
     */
    public String getMail() {
        return mail;
    }

    /**
     * Retourne le prénom de l'utilisateur.
     *
     * @return Le prénom de l'utilisateur.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Retourne le nom de l'utilisateur.
     *
     * @return Le nom de l'utilisateur.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Retourne l'Id de l'utilisateur.
     *
     * @return L'identifiant de l'utilisateur.
     */
    public Long getId() {
        return id;
    }

    /**
     * Retourne le mot de passe de l'utilisateur.
     *
     * @return Le mot de passe de l'utilisateur.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retourne le rôle de l'utilisateur.
     *
     * @return Le rôle de l'utilisateur.
     */
    @ManyToOne
    public Role getRole() {
        return role;
    }

    /**
     * Retourne la profession de l'utilisateur.
     *
     * @return La profession de l'utilisateur.
     */
    @ManyToOne
    public Work getWork() {
        return work;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + (role == null ? "null" : role.getName()) +
                ", type=" + work +
                ", region=" + region.getName()
                + '}';
    }

    // METHODS

    /**
     * Met à jour la région de l'utilisateur.
     *
     * @param region La nouvelle région.
     */
    public void setRegion(Region region) {
        this.region = region;
    }

    /**
     * Met à jour le mail de l'utilisateur.
     *
     * @param mail Le nouveau mail.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Met à jour le prénom de l'utilisateur.
     *
     * @param firstName Le nouveau prénom.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Met à jour le nom de l'utilisateur.
     *
     * @param lastName Le nouveau nom.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Met à jour l'Id de l'utilisateur.
     *
     * @param id Le nouvel Id.
     */
    private void setId(Long id) {
        this.id = id;
    }

    /**
     * Met à jour le mot de passe de l'utilisateur.
     *
     * @param password Le nouveau mot de passe.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Met à jour le role de l'utilisateur.
     *
     * @param role Le nouveau role.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Met à jour la profession de l'utilisateur.
     *
     * @param work La nouvelle profession.
     */
    public void setWork(Work work) {
        this.work = work;
    }
}