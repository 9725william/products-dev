package com.products.demo.api.v1.local.api_franchise.franchise.adapters.bd2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface FranchiseRepository extends JpaRepository< Franchise, Long> {

    @Transactional
    public List<?> findStockBySql(String sql, Class entityClass);
}
