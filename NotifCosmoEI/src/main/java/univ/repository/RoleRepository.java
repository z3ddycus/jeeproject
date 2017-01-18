package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Role;

/**
 * Le repository des roles.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Le role associé au nom : name.
     */
    Role findOneByName(String name);
}
