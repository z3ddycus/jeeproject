package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Component;
import univ.domain.Product;


public interface ComponentRepository extends JpaRepository<Component, Long> {
    Product findByName(String name);
}
