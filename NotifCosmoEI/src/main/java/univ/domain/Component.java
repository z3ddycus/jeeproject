package univ.domain;

import javax.persistence.*;


@Entity
@Table(name = "Component")
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Parent")
    private long parent;
}
