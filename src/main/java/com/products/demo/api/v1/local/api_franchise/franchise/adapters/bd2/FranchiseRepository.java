package com.products.demo.api.v1.local.api_franchise.franchise.adapters.bd2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepository extends JpaRepository< Franchise, Long > {
    
}

