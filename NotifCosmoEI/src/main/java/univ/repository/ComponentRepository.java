package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Component;

/**
 * <b>Repository pour les composants.</b>
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
public interface ComponentRepository extends JpaRepository<Component, Long> {
    /**
     * Le composant possédant comme propriété ayant name comme propriété name.
     *
     * @param name La propriété cherchée dans les composants.
     * @return Le composant correspondant.
     */
    Component findByName(String name);
}
