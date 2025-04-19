package com.products.demo.api.v1.local.api_franchise.franchise.adapters.bd2;

import jakarta.persistence.*;

@Entity
@Table(name = "franchise")
public class FranchiseStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sucursal_name;

    private String product_name;

    private Long stock;

    public FranchiseStock() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSucursal_name() {
        return sucursal_name;
    }

    public void setSucursal_name(String sucursal_name) {
        this.sucursal_name = sucursal_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "FranchiseStock [id=" + id + ", sucursal_name=" + sucursal_name + ", product_name=" + product_name
                + ", stock=" + stock + "]";
    }

    
    
}
