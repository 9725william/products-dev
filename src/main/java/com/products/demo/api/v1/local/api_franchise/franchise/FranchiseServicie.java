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
            Object franchiseExistObj = franchiseAdapter.getById(id);
            if (UtilsService.isErrorService(franchiseExistObj))
                return franchiseExistObj;

            if (franchiseExistObj == null) {
                return new ErrorService(
                        "La franquicia que intenta actualizar no existe",
                        "La franquicia con ID = " + id + " no existe",
                        myClassName);
            }

            Franchise franchiseExist = (Franchise) franchiseExistObj;

            franchiseExist.setName(CommisionDto.getName());

            String now = UtilsLocal.getDateTimeNow();
            Timestamp nowTimestamp = UtilsLocal.strDateToTimestamp(now, "yyyy-MM-dd HH:mm:ss");
            franchiseExist.setUpdated_at(nowTimestamp);

            Object resp = franchiseAdapter.createOrUpdate(franchiseExist);
            return resp;
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurrido un error actualizando la franquicia",
                    e.getMessage(),
                    myClassName);
        }
    }

    public Object franchiseTopStock(Long idFranchise) {
        try {

            Object resp = franchiseAdapter.franchiseTopStock(idFranchise);

            if (resp == null) {
                return new ErrorService(
                        "No se encontró el detalle de productos con mayor stock por sucursal con Id:"
                                + String.valueOf(idFranchise),
                        "",
                        myClassName,
                        200);
            }
            return resp;
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurridFranchiseo un error obteniendo el detalle de productos con mayor stock por sucursal",
                    e.getMessage(),
                    myClassName);
        }
    }

}
