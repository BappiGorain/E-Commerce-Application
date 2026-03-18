package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long>
{
    List<Address> findByUserEmail(String email);
}
