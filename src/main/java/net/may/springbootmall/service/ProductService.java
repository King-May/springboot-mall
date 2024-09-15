package net.may.springbootmall.service;

import net.may.springbootmall.dto.ProductRequest;
import net.may.springbootmall.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getProducts();

    Optional<Product> getProductById(int id);

    Product createProduct(ProductRequest product);

    void putProduct(Integer id, ProductRequest product);

    void deleteProduct(Integer id);
}
