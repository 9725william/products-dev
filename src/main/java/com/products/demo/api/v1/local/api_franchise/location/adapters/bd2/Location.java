package com.products.demo.api.v1.local.api_franchise.location.adapters.bd2;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "sucursal")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String country;
    private String city;
    private String municipality;
    private String address;
    private Boolean active = true;
    private Long franchise_id;
    private Timestamp created_at;
    private Timestamp updated_at;

    public Location() {
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
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getMunicipality() {
        return municipality;
    }
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public Long getFranchise_id() {
        return franchise_id;
    }
    public void setFranchise_id(Long franchise_id) {
        this.franchise_id = franchise_id;
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
        return "Location [id=" + id + ", name=" + name + ", country=" + country + ", city=" + city + ", municipality="
                + municipality + ", address=" + address + ", active=" + active + ", franchise_id=" + franchise_id
                + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
    }

    
}
