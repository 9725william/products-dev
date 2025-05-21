package com.products.demo.api.v1.local.api_franchise.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses; 
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/local/api-franchise/tests")
@CrossOrigin
@Tag(name = "Test", description = "test de comprobacion para verificar que la aplicacion este disponible ")
public class TestController {

    @Value("${enviroment.current}")
    private String enviroment;

    @GetMapping("")
    @Operation(
            summary = "test de funcionamiento",
            description = "retorna mensaje exitoso cuando la aplicacion esta funcional"
        )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La aplicación responde correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
        })
    public ResponseEntity<?> test(
            HttpServletRequest req) {
        String message = "Backend demo OK. " + enviroment;
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
