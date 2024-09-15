package net.may.springbootmall.service.impl;

import net.may.springbootmall.dao.ProductDao;
import net.may.springbootmall.dto.ProductRequest;
import net.may.springbootmall.model.Product;
import net.may.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public List<Product> getProducts() {
        return productDao.getProducts();
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productDao.getProductById(id);
    }

    @Override
    public Product createProduct(ProductRequest product) {
        return productDao.createProduct(product);
    }

    @Override
    public void putProduct(Integer id, ProductRequest product) {
        productDao.putProduct(id, product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productDao.deleteProduct(id);
    }
}
