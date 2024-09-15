package net.may.springbootmall.service;

import net.may.springbootmall.model.Product;

import java.util.Optional;

public interface ProductService {
    Optional<Product> getProductById(int id);
}
