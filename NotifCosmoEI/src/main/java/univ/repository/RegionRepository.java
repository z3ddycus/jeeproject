package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Region;

/**
 * <b>Le repository des régions.</b>
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
public interface RegionRepository extends JpaRepository<Region, Long> {
    /**
     * La région associé au nom name.
     *
     * @param name Le nom de la région recherchée.
     * @return La région correspondante.
     */
    Region findOneByName(String name);
}
