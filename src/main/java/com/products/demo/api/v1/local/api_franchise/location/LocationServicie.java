package com.products.demo.api.v1.local.api_franchise.location;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.demo.api.v1.local.api_franchise.franchise.adapters.bd2.Franchise;
import com.products.demo.api.v1.local.api_franchise.franchise.adapters.payloads.FranchiseDto;
import com.products.demo.api.v1.local.api_franchise.location.adapters.LocationAdapters;
import com.products.demo.api.v1.local.api_franchise.location.adapters.bd2.Location;
import com.products.demo.api.v1.local.api_franchise.location.adapters.payloads.LocationDto;
import com.products.demo.api.v1.local.api_franchise.utils.ErrorService;
import com.products.demo.api.v1.local.api_franchise.utils.UtilsLocal;
import com.products.demo.api.v1.local.api_franchise.utils.UtilsService;

@Service
public class LocationServicie {

    private String myClassName = LocationServicie.class.getName();

    @Autowired
    LocationAdapters locationAdapters;

    public Object create(LocationDto locationDto) {
        try {
            Location location = new Location();

            location.setName(locationDto.getName());
            location.setCountry(locationDto.getCountry());
            location.setCity(locationDto.getCity());
            location.setMunicipality(locationDto.getMunicipality());
            location.setAddress(locationDto.getAddress());
            location.setActive(true);
            location.setFranchise_id(locationDto.getFranchise_id());

            // Fechas actuales
            String now = UtilsLocal.getDateTimeNow();
            Timestamp nowTimestamp = UtilsLocal.strDateToTimestamp(now, "yyyy-MM-dd HH:mm:ss");

            location.setCreated_at(nowTimestamp);

            Object resp = locationAdapters.createOrUpdate(location);
            return resp;
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurrido un error guardando la franquicia",
                    e.getMessage(),
                    myClassName);
        }
    }

    public Object update(Long id, LocationDto locationDto) {
        try {
            Object locationExistObj = locationAdapters.getById(id);
            if (UtilsService.isErrorService(locationExistObj)) {
                return locationExistObj;
            }

            if (locationExistObj == null) {
                return new ErrorService(
                        "La sucursal que intenta actualizar no existe",
                        "La sucursal con ID = " + id + " no existe",
                        myClassName);
            }

            Location location = (Location) locationExistObj;

            // Solo actualiza el campo requerido
            location.setName(locationDto.getName());

            String now = UtilsLocal.getDateTimeNow();
            Timestamp nowTimestamp = UtilsLocal.strDateToTimestamp(now, "yyyy-MM-dd HH:mm:ss");
            location.setUpdated_at(nowTimestamp);

            Object resp = locationAdapters.createOrUpdate(location);
            return resp;
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurrido un error actualizando la sucursal",
                    e.getMessage(),
                    myClassName);
        }
    }

}
