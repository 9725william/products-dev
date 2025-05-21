package com.products.demo.api.v1.local.api_franchise.franchise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.demo.api.v1.local.api_franchise.franchise.adapters.payloads.FranchiseDto;
import com.products.demo.api.v1.local.api_franchise.logs_franchise.LogFranchiseService;
import com.products.demo.api.v1.local.api_franchise.utils.ResponseLocal;
import com.products.demo.api.v1.local.api_franchise.utils.UtilsLocal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("local/api-franchise/")
@CrossOrigin
@Tag(name = "sucursales", description = "Operaciones relacionadas con sucursales y franqucias")
public class FranchiseController {

    private String myClassName = FranchiseController.class.getName();

    @Autowired
    FranchiseServicie franchiseServicie;

    @Autowired
    LogFranchiseService logFranchiseService;

    @PostMapping("create")
    @Operation(
            summary = "creacion de sucursal",
            description = "Retorna un objto con status 200 al momento de crear una sucursal"
        )
    public ResponseEntity<?> create(
            @Valid @RequestBody FranchiseDto payload,
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
            Object resp = franchiseServicie.create(payload);

            HttpStatus httpStatus = response.validateService(resp,
                    "franquicia creada",
                    myClassName,
                    payload.toString(),
                    req);
            return new ResponseEntity<>(response, httpStatus);
        } catch (Exception e) {
            response.setError(
                    HttpStatus.BAD_REQUEST.value(),
                    "No se pudo crear la franquicia",
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
            summary = "actualizacion de sucursal",
            description = "Retorna un objto con status 200 al momento de actualizar una sucursal por su id"
        )
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody FranchiseDto payload,
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
            Object resp = franchiseServicie.update(id, payload);

            HttpStatus httpStatus = response.validateService(resp,
                    "Actualizacion de franquicia, ok",
                    this.myClassName,
                    payload.toString(),
                    req);
            return new ResponseEntity<>(response, httpStatus);
        } catch (Exception e) {
            response.setError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "No se pudo actualizar la franquicia",
                    e.getMessage(),
                    UtilsLocal.emptyErrorList(),
                    this.myClassName,
                    "",
                    req);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("by-topStock/{id}")
    @Operation(
            summary = "mostrar cual es el producto que\r\n"
            		+ "más stock tiene por sucursal para una franquicia puntual",
            description = "Retorna un objto con status 200 al momento uncionalidad que, a partir del ID de una franquicia, permite filtrar\r\n"
            		+ "las sucursales asociadas. Dentro de cada sucursal, se realiza un filtrado de\r\n"
            		+ "productos por nombre, mostrando aquellos con mayor cantidad en inventario de\r\n"
            		+ "cada una de las sucursales vinculadas a la franquicia"
        )
    public ResponseEntity<?> franchiseTopStock(

            @PathVariable("id") Long idFranchise,
            HttpServletRequest req) {

        String action = "getByIdSchema";
        ResponseLocal response = new ResponseLocal();

        try {
            Object resp = franchiseServicie.franchiseTopStock(idFranchise);

            HttpStatus httpStatus = response.validateService(
                    resp,
                    "Consulta de productos con mayor stock por sucursal asociada a una franquicia, ok",
                    this.myClassName,
                    "",
                    req);
            return new ResponseEntity<>(response, httpStatus);
        } catch (Exception e) {
            response.setError(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "No se pudo obtener el detalle de productos con mayor stock por sucursal",
                    e.getMessage(),
                    UtilsLocal.emptyErrorList(),
                    this.myClassName,
                    "",
                    req);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
