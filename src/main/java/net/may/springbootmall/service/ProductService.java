package net.may.springbootmall.service;

import net.may.springbootmall.dto.ProductRequest;
import net.may.springbootmall.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductById(int id);

    Product createProduct(ProductRequest product);

    void putProduct(Integer id, ProductRequest product);

    void deleteProduct(Integer id);
}
