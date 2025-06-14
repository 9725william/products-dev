package com.products.demo.api.v1.local.api_franchise.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.products.demo.api.v1.local.api_franchise.logs_franchise.LogFranchiseService;
import com.products.demo.api.v1.local.api_franchise.utils.ResponseLocal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@Tag(name = "clientes", description = "servicio de consulta de clientes con datos quemados")
@RequestMapping("local/api-clients/")
public class ClientsCrontoller {

    private String myClassName = ClientsCrontoller.class.getName();

    @Autowired
    ClientsServicie servicie;

    @Autowired
    LogFranchiseService logFranchiseService;

    @GetMapping("/consult/{tipo}/{numero}")
    @Operation(
            summary = "consulta de informacion de cliente",
            description = "Retorna un objto con status 200 al momento de consultar un usuario"
        )
    public ResponseEntity<?> consultarCliente(
            @PathVariable String documentType,
            @PathVariable String documentNumber,
            HttpServletRequest req) {

        ResponseLocal response = new ResponseLocal();

        try {
            Object result = servicie.getClients(documentType, documentNumber);

            HttpStatus status = response.validateService(
                    result,
                    "Consulta de cliente exitosa",
                    myClassName,
                    "",
                    req
            );
            return new ResponseEntity<>(response, status);

        } catch (Exception e) {
            response.setError(
                    HttpStatus.INTERNAL_SERVER_ERROR.value(),
                    "Error interno en la consulta del cliente",
                    e.getMessage(),
                    List.of(),
                    myClassName,
                    "",
                    req
            );
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
