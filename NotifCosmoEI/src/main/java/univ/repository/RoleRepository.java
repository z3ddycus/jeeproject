package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Role;

/**
 * <b>Le repository des roles.</b>
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Le role associé au nom : name.
     *
     * @param name Le nom du rôle recherché.
     * @return Le rôle correspondant au nom donné.
     */
    Role findOneByName(String name);
}
