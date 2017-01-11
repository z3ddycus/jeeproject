package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.entity.Work;

public interface WorkRepository extends JpaRepository<Work, Long> {
}
