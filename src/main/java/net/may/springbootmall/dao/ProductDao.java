package net.may.springbootmall.dao;

import net.may.springbootmall.dto.ProductRequest;
import net.may.springbootmall.model.Product;

import java.util.Optional;

public interface ProductDao {
    Optional<Product> getProductById(int id);

    Product createProduct(ProductRequest product);
}
