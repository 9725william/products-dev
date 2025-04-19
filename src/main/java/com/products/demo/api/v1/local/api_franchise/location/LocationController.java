package com.products.demo.api.v1.local.api_franchise.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.demo.api.v1.local.api_franchise.location.adapters.payloads.LocationDto;
import com.products.demo.api.v1.local.api_franchise.logs_franchise.LogFranchiseService;
import com.products.demo.api.v1.local.api_franchise.utils.ResponseLocal;
import com.products.demo.api.v1.local.api_franchise.utils.UtilsLocal;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("local/api-location/")
@CrossOrigin
public class LocationController {

    private String myClassName = LocationController.class.getName();

    @Autowired
    LocationServicie locationSerivicie;

    @Autowired
    LogFranchiseService logFranchiseService;

    @PostMapping("create")
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
}
