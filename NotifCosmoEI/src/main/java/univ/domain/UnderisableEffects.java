package univ.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UndesirableEffects")
public class UnderisableEffects implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Name")
    private String name;

}
