package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Region;

/**
 * Le repository des régions.
 */
public interface RegionRepository extends JpaRepository<Region, Long> {
    /**
     * La région associé au nom name.
     */
    Region findOneByName(String name);
}
