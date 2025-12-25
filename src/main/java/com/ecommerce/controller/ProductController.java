package com.ecommerce.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Product addProduct(@RequestBody Product product)
    {
        return productService.addProduct(product);
    }

    @GetMapping("getProduct/{productId}")
    public Product getProductById(@PathVariable("productId") Long productId)
    {
        return productService.getProductById(productId);
    }


    @GetMapping("/products")
    public List<Product> getAllProduct()
    {
        return productService.getAllProduct();
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product)
    {
        return productService.updateProduct(product);
    }
    

    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId)
    {
        productService.deleteProduct(productId);
    }

}
