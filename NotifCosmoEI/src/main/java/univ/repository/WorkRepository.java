package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Work;

/**
 * <b>Repository d'une profession.</b>
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
public interface WorkRepository extends JpaRepository<Work, Long> {
}
