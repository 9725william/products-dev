package com.products.demo.api.v1.local.api_franchise.location.adapters.payloads;

import jakarta.validation.constraints.*;

public class LocationDto {

    private String name;

    private String country;

    private String city;

    private String municipality;

    private String address;

    private Long franchise_id;

    public LocationDto() {
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

    public Long getFranchise_id() {
        return franchise_id;
    }

    public void setFranchise_id(Long franchise_id) {
        this.franchise_id = franchise_id;
    }

    @Override
    public String toString() {
        return "LocationDto [name=" + name + ", country=" + country + ", city=" + city + ", municipality="
                + municipality + ", address=" + address + ", franchise_id=" + franchise_id + "]";
    }

}
