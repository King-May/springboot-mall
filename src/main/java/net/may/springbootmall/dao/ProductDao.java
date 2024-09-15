package net.may.springbootmall.dao;

import net.may.springbootmall.dto.ProductQueryParams;
import net.may.springbootmall.dto.ProductRequest;
import net.may.springbootmall.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> getProducts(ProductQueryParams productQueryParams);

    Optional<Product> getProductById(int id);

    Product createProduct(ProductRequest product);

    void putProduct(Integer id, ProductRequest product);

    void deleteProduct(Integer id);
}
