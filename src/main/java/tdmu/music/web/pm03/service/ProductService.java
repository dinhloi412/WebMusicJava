package tdmu.music.web.pm03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tdmu.music.web.pm03.model.Product;
import tdmu.music.web.pm03.repository.ProductRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public void addProduct(Product product)
    {
        productRepository.save(product);
    }
    public void deleteProductById(long id)
    {
        productRepository.deleteById(id);
    }
    public Optional<Product> getProductById(long id)
    {
        return productRepository.findById(id);
    }
    public List<Product> getAllProductByCategory(int id)
    {
        return productRepository.findAllByCategoryId(id);
    }
    public List<Product> searchProductByName(String name)
    {
        return productRepository.findByNameContaining(name);
    }
    public Page<Product> findByNameContaining(String name, Pageable pageable)
    {
        return productRepository.findByNameContaining(name,pageable);
    }
    public Page<Product>findAll(Pageable pageable)
    {
        return productRepository.findAll(pageable);
    }

}
