package com.products.demo.api.v1.local.api_franchise.franchise;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.demo.api.v1.local.api_franchise.franchise.adapters.FranchiseAdapter;
import com.products.demo.api.v1.local.api_franchise.franchise.adapters.bd2.Franchise;
import com.products.demo.api.v1.local.api_franchise.franchise.adapters.payloads.FranchiseDto;
import com.products.demo.api.v1.local.api_franchise.utils.ErrorService;
import com.products.demo.api.v1.local.api_franchise.utils.UtilsLocal;

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
            franchise.setUpdated_at(nowTimestamp);

            Object resp = franchiseAdapter.createOrUpdate(franchise);
            return resp;
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurrido un error guardando la franquicia",
                    e.getMessage(),
                    myClassName);
        }
    }

}
