package com.ecommerce.service;

import java.util.List;
import com.ecommerce.model.Product;

public interface ProductService
{
    
    public Product getProductById(Long productId);

    public List<Product> getAllProduct();

    public List<Product> getProductByCategoryId(Long categoryId);

    public Product addProduct(Product product, Long categoryId);

    public Product updateProduct(Long productId,Product product);

    public void deleteProduct(Long productId);

}
