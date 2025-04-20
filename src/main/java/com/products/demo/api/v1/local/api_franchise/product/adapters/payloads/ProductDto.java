package com.products.demo.api.v1.local.api_franchise.product.adapters.payloads;

import jakarta.validation.constraints.*;

public class ProductDto {

    private String name;

    private Long stock;

    private Long sucursal_id;

    public ProductDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getSucursal_id() {
        return sucursal_id;
    }

    public void setSucursal_id(Long sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    @Override
    public String toString() {
        return "ProductDto [name=" + name + ", stock=" + stock + ", sucursal_id=" + sucursal_id + "]";
    }

}
