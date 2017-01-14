package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import univ.domain.Component;


@Transactional
public interface ComponentRepository extends JpaRepository<Component, Long> {
    Component findByName(String name);
}
