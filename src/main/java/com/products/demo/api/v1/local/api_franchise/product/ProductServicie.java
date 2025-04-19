package com.products.demo.api.v1.local.api_franchise.product;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.demo.api.v1.local.api_franchise.product.adapters.ProductAdapter;
import com.products.demo.api.v1.local.api_franchise.product.adapters.bd2.Product;
import com.products.demo.api.v1.local.api_franchise.product.adapters.payloads.ProductDto;
import com.products.demo.api.v1.local.api_franchise.utils.ErrorService;
import com.products.demo.api.v1.local.api_franchise.utils.UtilsLocal;
import com.products.demo.api.v1.local.api_franchise.utils.UtilsService;

@Service
public class ProductServicie {

    private String myClassName = ProductServicie.class.getName();

    @Autowired
    ProductAdapter ProductAdapter;

    public Object create(ProductDto productDto) {
        try {

            Product location = new Product();

            location.setName(productDto.getName());
            location.setStock(productDto.getStock());
            location.setActive(true);
            location.setSucursal_id(productDto.getSucursal_id());

            // Fechas actuales
            String now = UtilsLocal.getDateTimeNow();
            Timestamp nowTimestamp = UtilsLocal.strDateToTimestamp(now, "yyyy-MM-dd HH:mm:ss");

            location.setCreated_at(nowTimestamp);

            Object resp = ProductAdapter.createOrUpdate(location);
            return resp;
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurrido un error guardando el producto",
                    e.getMessage(),
                    myClassName);
        }
    }

    public Object update(Long id, ProductDto productDto) {
        try {
            Object franchiseExist = ProductAdapter.getById(id);
            if (UtilsService.isErrorService(franchiseExist)) {
                return franchiseExist;
            }

            if (franchiseExist == null) {
                return new ErrorService(
                        "El producto que intenta actualizar no existe",
                        "El producto con ID = " + id + " no existe",
                        myClassName);
            }

            Product location = new Product();
            location.setId(id);
            location.setName(productDto.getName()); // Solo actualizar el Nombre del producto y fecha de actulización"

            String now = UtilsLocal.getDateTimeNow();
            Timestamp nowTimestamp = UtilsLocal.strDateToTimestamp(now, "yyyy-MM-dd HH:mm:ss");
            location.setUpdated_at(nowTimestamp);

            Object resp = ProductAdapter.createOrUpdate(location);
            return resp;
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurrido un error actualizando el producto",
                    e.getMessage(),
                    myClassName);
        }
    }

    public Object updateStock(Long id, ProductDto productDto) {
        try {
            Object franchiseExist = ProductAdapter.getById(id);
            if (UtilsService.isErrorService(franchiseExist)) {
                return franchiseExist;
            }

            if (franchiseExist == null) {
                return new ErrorService(
                        "El producto que intenta actualizar no existe",
                        "El producto con ID = " + id + " no existe",
                        myClassName);
            }

            Product location = new Product();
            location.setId(id);
            location.setStock(productDto.getStock()); // Solo actualizar el stock del producto y fecha de actulización"

            String now = UtilsLocal.getDateTimeNow();
            Timestamp nowTimestamp = UtilsLocal.strDateToTimestamp(now, "yyyy-MM-dd HH:mm:ss");
            location.setUpdated_at(nowTimestamp);

            Object resp = ProductAdapter.createOrUpdate(location);
            return resp;
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurrido un error actualizando el producto",
                    e.getMessage(),
                    myClassName);
        }
    }

    public Object deleteProductById(Long id) {
        try {
            Object producExists = ProductAdapter.getById(id);
            if (UtilsService.isErrorService(producExists)) {
                return producExists;
            }

            if (producExists == null) {
                return new ErrorService(
                        "El producto que intenta eliminar no existe",
                        "El producto con ID = " + id + " no existe",
                        myClassName);
            }

            return ProductAdapter.deleteProductById(id);
        } catch (Exception e) {
            return new ErrorService(
                    "Ha ocurrido un error eliminando el producto",
                    e.getMessage(),
                    myClassName
            );
        }
    }

}
