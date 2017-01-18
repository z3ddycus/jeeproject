package univ.domain;

import javax.persistence.*;
import java.io.Serializable;

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
     * La region.
     */
    public Region getRegion() {
        return region;
    }

    /**
     * Le mail.
     */
    public String getMail() {
        return mail;
    }

    /**
     * Le prénom.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Le nom.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * L'id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Le password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Le role.
     */
    @ManyToOne
    public Role getRole() {
        return role;
    }

    /**
     * Le travail.
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
     * Setter de la région.
     */
    public void setRegion(Region region) {
        this.region = region;
    }

    /**
     * Setter du mail.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Setter du prénom.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Setter du nom.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Setter de l'id.
     */
    private void setId(Long id) {
        this.id = id;
    }

    /**
     * Setter du password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter du role.
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Setter du travail.
     */
    public void setWork(Work work) {
        this.work = work;
    }
}