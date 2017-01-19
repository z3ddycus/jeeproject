package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import univ.domain.Component;
import univ.domain.Effect;
import univ.domain.Region;
import univ.domain.User;

import java.util.Set;

/**
 * <b>Le repository des effets indésirables.</b>
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
public interface EffectRepository  extends JpaRepository<Effect, Long> {
    /**
     * Les effets ayant un utilisateur associé de la région : region.
     *
     * @param region La région dans laquelle nous cherchons les effets.
     * @return Les effets resensés d'une région.
     */
    @Query("SELECT e FROM Effect e WHERE e.user.region = ?1")
    Set<Effect> findByRegion(Region region);

    /**
     * Les effets associés au composant component.
     *
     * @param component Le composant sur lequel nous cherchons les effets.
     * @return Les effets associés au composant indiqué.
     */
    Set<Effect> findByComponent(Component component);

    /**
     * Les effets associés à l'utilisateur user.
     *
     * @param user L'utilisateur dont on cherche les effets.
     * @return Les effets associés à l'utilisateur donné.
     */
    Set<Effect> findByUser(User user);

    /**
     * Les effets associés à la description : description.
     *
     * @return Les descriptions des effets.
     */
    @Query("SELECT DISTINCT e.description FROM Effect e ORDER BY e.description")
    Set<String> findAllDescription();
}
