package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import univ.domain.entity.Product;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
