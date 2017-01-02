package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Component;


public interface ComponentRepository extends JpaRepository<Component, Long> {
}
