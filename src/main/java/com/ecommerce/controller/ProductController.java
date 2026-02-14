package com.ecommerce.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ecommerce.helper.ApiResponse;
import com.ecommerce.model.Product;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/product")
public class ProductController
{
    Logger logger = LoggerFactory.getLogger(ProductController.class);

    final private ProductService productService;
    final private CategoryService categoryService;

    ProductController(ProductService productService,CategoryService categoryService)
    {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    

   

    @GetMapping("/allProducts")
    public String allProducts(Model model)
    {
        System.out.println("All product");

        List<Product> allProduct = productService.getAllProduct();

        model.addAttribute("products",allProduct);

        return "allproducts";
    }

    @GetMapping("/addProduct")
    public String showAddProductPage(Model model)
    {
        logger.info("Add product page is loaded");
        model.addAttribute("product",new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "addproduct";
    }
    
    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product,
                            @RequestParam Long categoryId) {
                                
        logger.info("New Product Added");

        productService.addProduct(product, categoryId);
        return "redirect:/product/allProducts";
    }




    @GetMapping("getProduct/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId)
    {
        logger.info("product returned by productId : " + productId);
        return ResponseEntity.ok(productService.getProductById(productId));
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
        logger.info("Product deleted successfully with id : " + id);

        productService.deleteProduct(id);
        ApiResponse<Void> response = 
                new ApiResponse<>(true, "Product deleted successfully", null);

        return ResponseEntity.ok(response);
    }

}