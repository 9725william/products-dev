package com.products.demo.api.v1.local.api_franchise.franchise.adapters.bd2;

import java.util.List;

import com.products.demo.api.v1.local.api_franchise.utils.ErrorService;
import com.products.demo.api.v1.local.api_franchise.utils.UtilsLocal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class FranchiseRepositoryImpl {

    private String myClassName = FranchiseRepositoryImpl.class.getName();

    @PersistenceContext(unitName = "Bd2MysqlConfig")
    private EntityManager em;

    public Object findStockBySql(String sql, Class entityClass) {
        try {
            List<?> result = em.createNativeQuery(sql, entityClass).getResultList();
            return result;
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurrido un error realizando la interacción con la Base de Datos",
                    e.getMessage(),
                    myClassName,
                    500
            );
        }
    }

}
