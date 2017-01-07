package univ.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import univ.domain.Product;
import univ.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ComponentService componentService;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product get(String name) {
        return productRepository.findByName(name);
    }

    public Product get(long id) {
        return productRepository.findOne(id);
    }

    public boolean create(Product product) {
        Product p = productRepository.findByName(product.getName());
        if (p != null) {
            return false;
        }
        productRepository.save(product);
        return true;
    }
    public boolean update(Product product) {
        Product p = productRepository.findOne(product.getId());
        if (p == null || (!product.getName().equals(p.getName()) && productRepository.findByName(product.getName()) != null)) {
            return false;
        }
        p.setComponents(product.getComponents());
        p.setName(product.getName());
        productRepository.save(p);
        return true;
    }

    public void delete(Product p) {
        productRepository.delete(p);
    }
    public void delete(long id) {
        productRepository.delete(id);
    }
}
