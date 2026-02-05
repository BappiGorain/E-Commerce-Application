package com.ecommerce.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Category;
import com.ecommerce.model.Product;


@Repository
public interface ProductRepo extends JpaRepository<Product,Long>
{    
    // Custom finder methods

    List<Product> findByCategory(Category category);
}
