package com.products.demo.api.v1.local.api_franchise.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.demo.api.v1.local.api_franchise.location.adapters.payloads.LocationDto;
import com.products.demo.api.v1.local.api_franchise.logs_franchise.LogFranchiseService;
import com.products.demo.api.v1.local.api_franchise.utils.ResponseLocal;
import com.products.demo.api.v1.local.api_franchise.utils.UtilsLocal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("local/api-location/")
@CrossOrigin
@Tag(name = "location", description = "puntos de distribucion franquicias")
public class LocationController {

    private String myClassName = LocationController.class.getName();

    @Autowired
    LocationServicie locationSerivicie;

    @Autowired
    LogFranchiseService logFranchiseService;

    @PostMapping("create")
    @Operation(
            summary = "crear puntos de ventas",
            description = "Retorna un objto con status 200 al momento de crear una franquicia"
        )
    public ResponseEntity<?> create(
            @Valid @RequestBody LocationDto payload,
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
            Object resp = locationSerivicie.create(payload);

            HttpStatus httpStatus = response.validateService(resp,
                    "sucursal creada",
                    myClassName,
                    payload.toString(),
                    req);
            return new ResponseEntity<>(response, httpStatus);
        } catch (Exception e) {
            response.setError(
                    HttpStatus.BAD_REQUEST.value(),
                    "No se pudo crear la sucursal",
                    e.getMessage(),
                    UtilsLocal.emptyErrorList(),
                    myClassName,
                    payload.toString(),
                    req);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "update/{id}")
    @Operation(
            summary = "actualiza una fraquicia",
            description = "Retorna un objeto al momento de actulzar una franquicia por su id"
        )
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody LocationDto payload,
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
            Object resp = locationSerivicie.update(id, payload);

            HttpStatus httpStatus = response.validateService(resp,
                    "Actualizacion de sucursal, ok",
                    this.myClassName,
                    payload.toString(),
                    req);
            return new ResponseEntity<>(response, httpStatus);
        } catch (Exception e) {
            response.setError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "No se pudo actualizar la sucursal",
                    e.getMessage(),
                    UtilsLocal.emptyErrorList(),
                    this.myClassName,
                    "",
                    req);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
