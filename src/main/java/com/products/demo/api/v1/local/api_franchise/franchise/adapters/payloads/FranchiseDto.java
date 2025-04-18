package com.products.demo.api.v1.local.api_franchise.franchise.adapters.payloads;

import jakarta.validation.constraints.NotBlank;

public class FranchiseDto {

    @NotBlank(message = "El nombre de la franquicia no puede estar vacío")
    private String name;

    @NotBlank(message = "El país no puede estar vacío")
    private String country;

    @NotBlank(message = "La ciudad no puede estar vacía")
    private String city;

    @NotBlank(message = "El municipio no puede estar vacío")
    private String municipality;

    @NotBlank(message = "La dirección no puede estar vacía")
    private String address;

    public FranchiseDto() {
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

    @Override
    public String toString() {
        return "FranchiseDto [name=" + name + ", country=" + country + ", city=" + city + ", municipality="
                + municipality + ", address=" + address + "]";
    }

    
}