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

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product get(String name) {
        return productRepository.findByName(name);
    }

    public Product get(long id) {
        return productRepository.findOne(id);
    }

    public void create(Product product) {
        productRepository.save(product);
    }
}
