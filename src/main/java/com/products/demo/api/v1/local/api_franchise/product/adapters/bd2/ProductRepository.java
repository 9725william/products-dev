package com.products.demo.api.v1.local.api_franchise.product.adapters.bd2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository< Product, Long > {
    
}

