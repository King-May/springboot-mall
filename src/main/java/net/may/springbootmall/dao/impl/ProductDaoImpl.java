package net.may.springbootmall.dao.impl;

import net.may.springbootmall.dao.ProductDao;
import net.may.springbootmall.dto.ProductQueryParams;
import net.may.springbootmall.dto.ProductRequest;
import net.may.springbootmall.model.Product;
import net.may.springbootmall.rowmapper.ProductRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class ProductDaoImpl implements ProductDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getProducts(ProductQueryParams productQueryParams) {
        String sql = "select product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date from product where 1 = 1";

        MapSqlParameterSource params = new MapSqlParameterSource();

        if (productQueryParams.getCategory() != null) {
            sql += " and category = :category";
            params.addValue("category", productQueryParams.getCategory().name());
        }

        if (productQueryParams.getSearch() != null) {
            sql += " and product_name like :search";
            params.addValue("search", "%" + productQueryParams.getSearch() + "%");
        }

        return jdbcTemplate.query(sql, params, ProductRowMapper.INSTANCE);
    }

    @Override
    public Optional<Product> getProductById(int id) {
        String sql = "select product_id, product_name, category, image_url, price, stock, description, created_date, last_modified_date from product where product_id = :id";
        return jdbcTemplate.query(sql, new MapSqlParameterSource("id", id), ProductRowMapper.INSTANCE)
                .stream()
                .findFirst();
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product (product_name, category, image_url, price, stock, description, created_date, last_modified_date) " +
                "VALUES (:product_name, :category, :image_url, :price, :stock, :description, :created_date, :last_modified_date);";

        Date now = new Date();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("product_name", productRequest.getProductName())
                .addValue("category", productRequest.getCategory().name())
                .addValue("image_url", productRequest.getImagUrl())
                .addValue("price", productRequest.getPrice())
                .addValue("stock", productRequest.getStock())
                .addValue("description", productRequest.getDescription())
                .addValue("created_date", now)
                .addValue("last_modified_date", now);

        jdbcTemplate.update(sql, params, keyHolder);

        Product product = Product.createProductFromProductRequest(productRequest);
        product.setProductId(keyHolder.getKey().intValue());
        product.setCreatedDate(now);
        product.setLastModifiedDate(now);

        return product;
    }

    @Override
    public void putProduct(Integer id, ProductRequest productRequest) {
        String sql = "UPDATE product SET product_name = :product_name, category = :category, image_url = :image_url, price = :price, stock = :stock, description = :description, last_modified_date = :last_modified_date WHERE product_id = :product_id";

        Date now = new Date();

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("product_name", productRequest.getProductName())
                .addValue("category", productRequest.getCategory().name())
                .addValue("image_url", productRequest.getImagUrl())
                .addValue("price", productRequest.getPrice())
                .addValue("stock", productRequest.getStock())
                .addValue("description", productRequest.getDescription())
                .addValue("last_modified_date", now)
                .addValue("product_id", id);

        jdbcTemplate.update(sql, params);
    }

    @Override
    public void deleteProduct(Integer id) {
        String sql = "DELETE FROM product WHERE product_id = :product_id";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("product_id", id);

        jdbcTemplate.update(sql, params);
    }
}
