package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.User;

/**
 * <b>Le repository des utilisateurs.</b>
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * L'utilisateur associé à l'adresse email : mail.
     *
     * @param mail Le mail que nous cherchons.
     * @return L'utilisateur correspondant au mail.
     */
    User findByMail(String mail);
}
