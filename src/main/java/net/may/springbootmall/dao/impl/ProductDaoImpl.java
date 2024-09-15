package net.may.springbootmall.dao.impl;

import net.may.springbootmall.dao.ProductDao;
import net.may.springbootmall.model.Product;
import net.may.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductDaoImpl implements ProductDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Product> getProductById(int id) {
        String sql = "select product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date from product where product_id = :id";
        try {
            Product product = jdbcTemplate.queryForObject(sql, new MapSqlParameterSource("id", id), ProductRowMapper.INSTANCE);
            return Optional.ofNullable(product);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
