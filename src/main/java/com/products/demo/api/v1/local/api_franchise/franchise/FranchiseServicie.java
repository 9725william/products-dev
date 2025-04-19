package com.products.demo.api.v1.local.api_franchise.franchise;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.demo.api.v1.local.api_franchise.franchise.adapters.FranchiseAdapter;
import com.products.demo.api.v1.local.api_franchise.franchise.adapters.bd2.Franchise;
import com.products.demo.api.v1.local.api_franchise.franchise.adapters.payloads.FranchiseDto;
import com.products.demo.api.v1.local.api_franchise.utils.ErrorService;
import com.products.demo.api.v1.local.api_franchise.utils.UtilsLocal;
import com.products.demo.api.v1.local.api_franchise.utils.UtilsService;

@Service
public class FranchiseServicie {
    private String myClassName = FranchiseServicie.class.getName();

    @Autowired
    FranchiseAdapter franchiseAdapter;

    public Object create(FranchiseDto franchiseDto) {
        try {
            Franchise franchise = new Franchise();

            franchise.setName(franchiseDto.getName());
            franchise.setCountry(franchiseDto.getCountry());
            franchise.setCity(franchiseDto.getCity());
            franchise.setMunicipality(franchiseDto.getMunicipality());
            franchise.setAddress(franchiseDto.getAddress());
            franchise.setActive(true);

            // Fechas actuales
            String now = UtilsLocal.getDateTimeNow();
            Timestamp nowTimestamp = UtilsLocal.strDateToTimestamp(now, "yyyy-MM-dd HH:mm:ss");

            franchise.setCreated_at(nowTimestamp);

            Object resp = franchiseAdapter.createOrUpdate(franchise);
            return resp;
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurrido un error guardando la franquicia",
                    e.getMessage(),
                    myClassName);
        }
    }

    public Object update(Long id, FranchiseDto CommisionDto) {
        try {
            Object franchiseExist = franchiseAdapter.getById(id);
            if (UtilsService.isErrorService(franchiseExist))
                return franchiseExist;
    
            if (franchiseExist == null) {
                return new ErrorService(
                        "la franquicia que intenta actualizar no existe",
                        "la franquicia con ID = " + id + " no existe",
                        myClassName);
            }
    
            Franchise Franchise = new Franchise();
            Franchise.setId(id);
            Franchise.setName(CommisionDto.getName()); // Solo actualizar el Nombre de la franqucia y fecha de actualizacion"

            String now = UtilsLocal.getDateTimeNow();
            Timestamp nowTimestamp = UtilsLocal.strDateToTimestamp(now, "yyyy-MM-dd HH:mm:ss");
            Franchise.setUpdated_at(nowTimestamp);
    
            Object resp = franchiseAdapter.createOrUpdate(Franchise); 
            return resp;
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurrido un error actualizando la franquicia",
                    e.getMessage(),
                    myClassName);
        }
	}

}
