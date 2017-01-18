package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import univ.domain.Component;
import univ.domain.Effect;
import univ.domain.Region;
import univ.domain.User;

import java.util.Set;

/**
 * Le repository des effets indésirables.
 */
public interface EffectRepository  extends JpaRepository<Effect, Long> {
    /**
     * Les effets ayant un utilisateur associé de la région : region.
     */
    @Query("SELECT e FROM Effect e WHERE e.user.region = ?1")
    Set<Effect> findByRegion(Region region);

    /**
     * Les effets associés au composant component.
     */
    Set<Effect> findByComponent(Component component);

    /**
     * Les effets associés à l'utilisateur user.
     */
    Set<Effect> findByUser(User user);

    /**
     * Les effets associés à la description : description.
     */
    @Query("SELECT DISTINCT e.description FROM Effect e ORDER BY e.description")
    Set<String> findAllDescription();
}
