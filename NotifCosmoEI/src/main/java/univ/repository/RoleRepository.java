package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findOneByName(String name);
}
