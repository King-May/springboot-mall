package net.may.springbootmall.service.impl;

import net.may.springbootmall.dao.ProductDao;
import net.may.springbootmall.model.Product;
import net.may.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Optional<Product> getProductById(int id) {
        return productDao.getProductById(id);
    }
}
