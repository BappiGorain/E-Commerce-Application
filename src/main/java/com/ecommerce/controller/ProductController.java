package com.ecommerce.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.helper.ApiResponse;
import com.ecommerce.model.Product;
import com.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/product")
public class ProductController
{
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product)
    {
        logger.info("New Product Added");
        return ResponseEntity.ok(productService.addProduct(product));
    }

    @GetMapping("getProduct/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId)
    {
        logger.info("product returned by productId : " + productId);
        return ResponseEntity.ok(productService.getProductById(productId));
    }


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct()
    {
        logger.info("All product returned");
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId,@RequestBody Product product)
    {
        logger.info("Product updated with Id : " + productId);

        Product updatedProduct = productService.updateProduct(productId,product);
        return ResponseEntity.ok(updatedProduct);
    }
    

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable("productId") Long id)
    {
        logger.info("Product deleted successfully with id : " + productService);

        productService.deleteProduct(id);
        ApiResponse<Void> response = 
                new ApiResponse<>(true, "Product deleted successfully", null);

        return ResponseEntity.ok(response);
    }

}
