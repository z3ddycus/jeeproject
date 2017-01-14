package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Component;
import univ.domain.Region;
import univ.domain.User;

import java.util.List;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Region findOneByName(String name);
}
