package com.products.demo.api.v1.local.api_franchise.franchise.adapters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.demo.api.v1.local.api_franchise.franchise.adapters.bd2.Franchise;
import com.products.demo.api.v1.local.api_franchise.franchise.adapters.bd2.FranchiseRepository;
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

}
