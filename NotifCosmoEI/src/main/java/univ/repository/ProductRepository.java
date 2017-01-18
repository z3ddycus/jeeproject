package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Product;

/**
 * Le repository associé aux produits.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Le produit associé au nom : name.
     */
    Product findByName(String name);
}
