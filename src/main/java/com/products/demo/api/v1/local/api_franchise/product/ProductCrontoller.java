package com.products.demo.api.v1.local.api_franchise.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.demo.api.v1.local.api_franchise.logs_franchise.LogFranchiseService;
import com.products.demo.api.v1.local.api_franchise.product.adapters.payloads.ProductDto;
import com.products.demo.api.v1.local.api_franchise.utils.ResponseLocal;
import com.products.demo.api.v1.local.api_franchise.utils.UtilsLocal;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("local/api-product/")
@CrossOrigin
public class ProductCrontoller {

    private String myClassName = ProductCrontoller.class.getName();

    @Autowired
    ProductServicie productServicie;

    @Autowired
    LogFranchiseService logFranchiseService;

    @PostMapping("create")
    public ResponseEntity<?> create(
            @Valid @RequestBody ProductDto payload,
            BindingResult bindingResult,
            HttpServletRequest req) {
        String action = "create";

        ResponseLocal response = new ResponseLocal(logFranchiseService, action);

        if (bindingResult.hasErrors()) {
            response.setError(
                    HttpStatus.BAD_REQUEST.value(), "", "",
                    bindingResult.getAllErrors(),
                    myClassName,
                    payload.toString(),
                    req);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            Object resp = productServicie.create(payload);

            HttpStatus httpStatus = response.validateService(resp,
                    "producto creado",
                    myClassName,
                    payload.toString(),
                    req);
            return new ResponseEntity<>(response, httpStatus);
        } catch (Exception e) {
            response.setError(
                    HttpStatus.BAD_REQUEST.value(),
                    "No se pudo crear la producto",
                    e.getMessage(),
                    UtilsLocal.emptyErrorList(),
                    myClassName,
                    payload.toString(),
                    req);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductDto payload,
            BindingResult bindingResult,
            HttpServletRequest req) {

        String action = "update";
        ResponseLocal response = new ResponseLocal();

        // Validación de Dto ---------------------------------------------
        if (bindingResult.hasErrors()) {
            response.setError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "", "",
                    bindingResult.getAllErrors(),
                    this.myClassName,
                    payload.toString(),
                    req);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            Object resp = productServicie.update(id, payload);

            HttpStatus httpStatus = response.validateService(resp,
                    "Actualizacion de producto, ok",
                    this.myClassName,
                    payload.toString(),
                    req);
            return new ResponseEntity<>(response, httpStatus);
        } catch (Exception e) {
            response.setError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "No se pudo actualizar el producto",
                    e.getMessage(),
                    UtilsLocal.emptyErrorList(),
                    this.myClassName,
                    "",
                    req);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "update-sotock/{id}")
    public ResponseEntity<?> updateStock(
            @PathVariable Long id,
            @Valid @RequestBody ProductDto payload,
            BindingResult bindingResult,
            HttpServletRequest req) {

        String action = "update";
        ResponseLocal response = new ResponseLocal();

        // Validación de Dto ---------------------------------------------
        if (bindingResult.hasErrors()) {
            response.setError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "", "",
                    bindingResult.getAllErrors(),
                    this.myClassName,
                    payload.toString(),
                    req);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            Object resp = productServicie.update(id, payload);

            HttpStatus httpStatus = response.validateService(resp,
                    "Actualizacion de stock del producto, ok",
                    this.myClassName,
                    payload.toString(),
                    req);
            return new ResponseEntity<>(response, httpStatus);
        } catch (Exception e) {
            response.setError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "No se pudo actualizar el stock del producto",
                    e.getMessage(),
                    UtilsLocal.emptyErrorList(),
                    this.myClassName,
                    "",
                    req);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable Long id,
            HttpServletRequest req
    ) {
        String action = "delete";
        ResponseLocal response = new ResponseLocal(logFranchiseService, action);
        try {
            Object resp = productServicie.deleteProductById(id);

            HttpStatus httpStatus = response.validateService(resp,
                    "Producto eliminado",
                    this.myClassName,
                    "",
                    req
            );
            return new ResponseEntity(response, httpStatus);
        } catch (Exception e) {
            response.setError(HttpStatus.BAD_REQUEST.value(),
                    "No se pudo eliminar el prodcuto",
                    e.getMessage(),
                    UtilsLocal.emptyErrorList(),
                    this.myClassName,
                    "",
                    req
            );
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
