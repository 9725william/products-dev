package com.products.demo.api.v1.local.api_franchise.product.adapters.bd2;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long stock;
    private Boolean active = true;
    private Long sucursal_id;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getSucursal_id() {
        return sucursal_id;
    }

    public void setSucursal_id(Long sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", stock=" + stock + ", active=" + active + ", sucursal_id="
                + sucursal_id + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
    }

}
