package com.products.demo.api.v1.local.api_franchise.location.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.demo.api.v1.local.api_franchise.location.adapters.bd2.Location;
import com.products.demo.api.v1.local.api_franchise.location.adapters.bd2.LocationRepository;
import com.products.demo.api.v1.local.api_franchise.utils.ErrorService;

@Service
public class LocationAdapters {

    private String myClassName = LocationAdapters.class.getName();

    @Autowired
    LocationRepository locationRepository;

    public Object createOrUpdate(Location location) {
        try {
            Object resp = locationRepository.save(location);
            return resp;
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurrido un error guardando la franquicia",
                    e.getMessage(),
                    myClassName);
        }
    }
}
