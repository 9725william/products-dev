package com.products.demo.api.v1.local.api_franchise.franchise.adapters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.demo.api.v1.local.api_franchise.franchise.adapters.bd2.Franchise;
import com.products.demo.api.v1.local.api_franchise.franchise.adapters.bd2.FranchiseRepository;
import com.products.demo.api.v1.local.api_franchise.franchise.adapters.bd2.FranchiseStock;
import com.products.demo.api.v1.local.api_franchise.utils.ErrorService;

@Service
public class FranchiseAdapter {

    private String myClassName = FranchiseAdapter.class.getName();

    @Autowired
    FranchiseRepository franchiseRepository;

    public Object createOrUpdate(Franchise franchise) {
        try {
            Object resp = franchiseRepository.save(franchise);
            return resp;
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurrido un error guardando la franquicia",
                    e.getMessage(),
                    myClassName);
        }
    }

    public Object getById(Long id) {
        try {
            Optional<Franchise> resp = franchiseRepository.findById(id);
            if (!resp.isPresent()) {
                return null;
            }
            return resp.get();
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurrido un error buscando el registro",
                    e.getMessage(),
                    myClassName);
        }
    }

    public Object franchiseTopStock(Long idFranchise) {
        try {
            String sql = String.format(
                    "SELECT "
                    + "s.name AS sucursal_name, "
                    + "p.name AS product_name, "
                    + "p.stock "
                    + "FROM products p "
                    + "JOIN sucursal s ON p.sucursal_id = s.id "
                    + "JOIN franchise f ON s.franchise_id = f.id "
                    + "WHERE p.active = TRUE "
                    + "AND s.active = TRUE "
                    + "AND f.active = TRUE "
                    + "AND f.id = %d "
                    + "AND p.stock = ( "
                    + "SELECT MAX(p2.stock) "
                    + "FROM products p2 "
                    + "WHERE p2.sucursal_id = s.id "
                    + "AND p2.active = TRUE "
                    + ") "
                    + "ORDER BY s.name",
                    idFranchise
            );

            return franchiseRepository.findStockBySql(sql, FranchiseStock.class);
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurrido un error obteniendo el detalle de mayor stock por sucursal",
                    e.getMessage(),
                    myClassName
            );
        }
    }

}
