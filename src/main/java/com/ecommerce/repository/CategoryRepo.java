package com.ecommerce.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long>
{
    // Custom finder method

    
}
