package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import univ.domain.Component;
import univ.domain.Effect;
import univ.domain.Region;
import univ.domain.User;

import java.util.Set;

public interface EffectRepository  extends JpaRepository<Effect, Long> {

    @Query("SELECT e FROM Effect e WHERE e.user.region = ?2 and e.component = ?1")
    Set<Effect> findByRegionAndComponent(Component component, Region region);
    @Query("SELECT e FROM Effect e WHERE e.user.region = ?1")
    Set<Effect> findByRegion(Region region);
    Set<Effect> findByComponent(Component component);
    Set<Effect> findByUser(User user);

    @Query("SELECT DISTINCT e.description FROM Effect e ORDER BY e.description")
    Set<String> findAllDescription();
}
