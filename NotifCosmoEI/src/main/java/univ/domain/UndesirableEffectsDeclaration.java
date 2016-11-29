package univ.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "UndesirableEffectsDeclaration")
public class UndesirableEffectsDeclaration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "User")
    private long user;

    @Column(name = "UndesirableEffects")
    private long effect;

    @Column(name = "Date")
    private LocalDate date;
}
