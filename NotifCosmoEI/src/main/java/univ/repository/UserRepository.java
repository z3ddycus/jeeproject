package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByMail(String mail);
}
