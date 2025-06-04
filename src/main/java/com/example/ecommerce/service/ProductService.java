package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepo;

    public ProductService(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setCategory(updatedProduct.getCategory());
        product.setStockQuantity(updatedProduct.getStockQuantity());
        return productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        productRepo.delete(product);
    }

    public Product getProduct(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public List<Product> searchByName(String name) {
        return productRepo.findByNameContainingIgnoreCase(name);
    }

    public List<Product> filterByCategory(String category) {
        return productRepo.findByCategory(category);
    }

    public List<Product> filterByPriceRange(Double min, Double max) {
        return productRepo.findByPriceBetween(min, max);
    }
}
