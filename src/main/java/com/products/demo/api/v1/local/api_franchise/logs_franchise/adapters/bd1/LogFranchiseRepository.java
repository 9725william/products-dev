package com.products.demo.api.v1.local.api_franchise.logs_franchise.adapters.bd1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LogFranchiseRepository extends JpaRepository< LogFranchise, Integer > {
}
