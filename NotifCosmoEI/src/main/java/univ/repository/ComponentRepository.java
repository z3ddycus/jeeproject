package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Component;

/**
 * Repository pour les composants.
 */
public interface ComponentRepository extends JpaRepository<Component, Long> {
    /**
     * Le composant possédant comme propriété ayant name comme propriété name.
     */
    Component findByName(String name);
}
