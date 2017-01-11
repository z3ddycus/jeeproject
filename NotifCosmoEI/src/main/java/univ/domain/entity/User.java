package univ.domain.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_id")
public class User implements Serializable {

    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(unique=true)
    private String mail;
    private String firstName;
    private String lastName;
    private String password;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    private String region;
    @ManyToOne
    private Role role;
    @ManyToOne
    private Work work;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToOne
    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
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
                '}';
    }
}