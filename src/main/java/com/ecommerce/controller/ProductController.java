package com.ecommerce.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.model.Product;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger =
            LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService,
                             CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // ========================= LIST PRODUCTS =========================

    @GetMapping("/allProducts")
    public String showAllProducts(Model model) {

        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);

        logger.info("Loaded all products");

        return "admin/allproducts";
    }

    // ========================= ADD PRODUCT =========================

    @GetMapping("/addProduct")
    public String showAddProductPage(Model model) {

        model.addAttribute("product", new Product());
        model.addAttribute("categories",
                categoryService.getAllCategories());

        logger.info("Add product page loaded");

        return "admin/addproduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam Long categoryId) {

        productService.addProduct(product, categoryId);

        logger.info("Product added successfully");

        return "redirect:/product/allProducts";
    }

    // ========================= UPDATE PRODUCT =========================

    @GetMapping("/updateProduct/{id}")
    public String showUpdateProductPage(@PathVariable Long id,
                                        Model model) {

        Product product = productService.getProductById(id);

        model.addAttribute("product", product);
        model.addAttribute("categories",
                categoryService.getAllCategories());

        logger.info("Update page opened for product id: {}", id);

        return "admin/updateproduct";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute Product product,
                                @RequestParam Long categoryId) {

        productService.updateProduct(product.getId(),
                                     product,
                                     categoryId);

        logger.info("Product updated successfully, id: {}",
                product.getId());

        return "redirect:/product/allProducts";
    }

    // ========================= DELETE PRODUCT =========================

    @GetMapping("/deleteProduct/{id}")
    public String showDeleteProductPage(@PathVariable Long id,
                                        Model model) {

        Product product = productService.getProductById(id);
        model.addAttribute("product", product);

        logger.info("Delete confirmation page opened for product id: {}", id);

        return "admin/deleteproduct";
    }

   @PostMapping("/deleteProduct/{id}")
public String deleteProduct(@PathVariable Long id) {

    productService.deleteProductById(id);

    logger.info("Product deleted successfully, id: {}", id);

    return "redirect:/product/allProducts";
}

}
