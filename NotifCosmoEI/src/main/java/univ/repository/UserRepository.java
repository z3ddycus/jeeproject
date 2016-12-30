package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
