package univ.domain;

import javax.persistence.*;


@Entity
@Table(name = "ComponentProduct")
public class ComponentProduct {

    @Column(name = "Component")
    private long component;

    @Column(name = "Product")
    private long product;
}
