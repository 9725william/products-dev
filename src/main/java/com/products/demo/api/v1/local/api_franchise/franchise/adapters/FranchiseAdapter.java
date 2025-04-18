package com.products.demo.api.v1.local.api_franchise.franchise.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
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
                    "Ha ocurrido un error guardando la tarea.",
                    e.getMessage(),
                    myClassName
            );
        }
    }

}
