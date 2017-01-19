package univ.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.domain.Product;

/**
 * <b>Le repository associé aux produits.</b>
 *
 * @author Yohann Henry - Jeremie Pantin
 * @version 1.0
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Le produit associé au nom : name.
     *
     * @param name Le nom du produit recherché.
     * @return Le produit correspondant au nom donné.
     */
    Product findByName(String name);
}
