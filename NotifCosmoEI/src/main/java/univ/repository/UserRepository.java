package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.User;

/**
 * Le repository des utilisateurs.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * L'utilisateur associé à l'adresse email : mail.
     */
    User findByMail(String mail);
}
