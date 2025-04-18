package com.products.demo.api.v1.local.api_franchise.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/local/api-franchise/tests")
@CrossOrigin
public class TestController {

    @Value("${enviroment.current}")
    private String enviroment;

    @GetMapping("")
    public ResponseEntity<?> test(
            HttpServletRequest req
    ) {
        String message = "Backend de demo OK. " + enviroment;
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
