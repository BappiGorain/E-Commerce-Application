package com.ecommerce.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.model.Product;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/product")
public class AdminProductController {

    private static final Logger logger = LoggerFactory.getLogger(AdminProductController.class);

    private final ProductService productService;
    private final CategoryService categoryService;

    public AdminProductController(ProductService productService,
            CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // ================= LIST PRODUCTS =================

    @GetMapping("/allProducts")
    public String showAllProducts(Model model) {

        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);

        logger.info("Loaded all products");

        return "admin/allproducts";
    }

    // ================= ADD PRODUCT =================

    @GetMapping("/addProduct")
    public String showAddProductPage(Model model) {

        model.addAttribute("product", new Product());
        model.addAttribute("categories",
                categoryService.getAllCategories());

        logger.info("Add product page loaded");

        return "admin/addproduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(
            @ModelAttribute Product product,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("imageFile") MultipartFile file) {

        try 
        {

            if (!file.isEmpty())
            {

                String uploadDir = "src/main/resources/static/images/products/";

                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path filePath = uploadPath.resolve(fileName);

                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                product.setImage(fileName);
            }
        }

        catch (IOException e) 
        {
            e.printStackTrace();
        }

        productService.addProduct(product, categoryId);

        return "redirect:/admin/product/allProducts";
    }

    // ================= UPDATE PRODUCT =================

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
public String updateProduct(@Valid @ModelAttribute Product product,
                            BindingResult result,
                            @RequestParam Long categoryId,
                            Model model,
                            @RequestParam("imageFile") MultipartFile file) {

    if (result.hasErrors()) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/updateproduct";
    }

    try {

        if (!file.isEmpty()) 
        {

            String uploadDir = "src/main/resources/static/images/products/";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            product.setImage(fileName);

        } else {

            // KEEP OLD IMAGE
            Product existingProduct = productService.getProductById(product.getId());
            product.setImage(existingProduct.getImage());

        }

    } catch (IOException e) {
        e.printStackTrace();
    }

    productService.updateProduct(product.getId(), product, categoryId);

    return "redirect:/admin/product/allProducts";
}

    // ================= DELETE PRODUCT =================

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

        return "redirect:/admin/product/allProducts";
    }
}