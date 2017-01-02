package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
