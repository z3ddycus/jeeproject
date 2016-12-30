package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
