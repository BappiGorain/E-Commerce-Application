package com.ecommerce.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CategoryRepo;
import com.ecommerce.repository.ProductRepo;
import com.ecommerce.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService
{

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Product getProductById(Long productId) 
    {
        Product product = productRepo.findById(productId).orElseThrow(()-> new ResourceNotFoundException("product Not found with id : " + productId));
         return product;
    }

    @Override
    public List<Product> getAllProduct()
    {
        List<Product> allProducts = productRepo.findAll();   

        return allProducts;
    }

    @Override
    public Product addProduct(Product product, Long categoryId)
    {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category Not Found with id :" + categoryId));
        product.setCategory(category);
        Product savedProduct = productRepo.save(product);

        return savedProduct;

    }

    @Override
    public Product updateProduct(Long productId,Product product)
    {
        Product existingProduct = productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Resource Not Found"));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setBrand(product.getBrand());
        existingProduct.setManufactureDate(product.getManufactureDate());
        existingProduct.setExpirationDate(product.getExpirationDate());
        existingProduct.setUnitLeft(product.getUnitLeft());

        Product savedProduct = productRepo.save(existingProduct);

        return savedProduct;
    }

    @Override
    public void deleteProduct(Long productId)
    {
        Product product =  productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("product Not Found with Id : " + productId));
        productRepo.delete(product);
    }

    @Override
    public List<Product> getProductByCategoryId(Long categoryId)
    {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category Not Found with id : " + categoryId));
       
        List<Product> products = productRepo.findByCategory(category);

        return products;
    }

     
}
