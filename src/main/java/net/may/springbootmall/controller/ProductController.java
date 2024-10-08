package net.may.springbootmall.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import net.may.springbootmall.constant.ProductCategory;
import net.may.springbootmall.dto.ProductQueryParams;
import net.may.springbootmall.dto.ProductRequest;
import net.may.springbootmall.model.Product;
import net.may.springbootmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,

            @RequestParam(required = false, defaultValue = "created_date") String order,
            @RequestParam(required = false, defaultValue = "desc") String sort,

            @RequestParam(required = false, defaultValue = "5") @Max(100) @Min(1) Integer limit,
            @RequestParam(required = false, defaultValue = "0") @Min(1) Integer offset
    ) {

        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrder(order);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        return ResponseEntity.ok(productService.getProducts(productQueryParams));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/product/")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Product product = productService.createProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> putProduct(@PathVariable Integer id, @RequestBody @Valid ProductRequest productRequest) {
        productService.putProduct(id, productRequest);
        return ResponseEntity.ok(productService.getProductById(id).get());
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> putProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
