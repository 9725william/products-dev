package com.products.demo.api.v1.local.api_franchise.clients;

import org.springframework.stereotype.Service;

import com.products.demo.api.v1.local.api_franchise.clients.adapters.responses.Clients;
import com.products.demo.api.v1.local.api_franchise.utils.ErrorService;

@Service
public class ClientsServicie {

    private String myClassName = ClientsServicie.class.getName();

    public Object getClients(String documentType, String documentNumber) {
        try {
            if (documentType == null || documentNumber == null || documentType.isBlank() || documentNumber.isBlank()) {
                return new ErrorService("documentType y número de documento son obligatorios", "", myClassName, 400);
            }
            if (!documentType.equalsIgnoreCase("C") && !documentType.equalsIgnoreCase("P")) {
                return new ErrorService("Tipo de documento inválido. Solo se permite C o P", "", myClassName, 400);
            }
            if (documentType.equalsIgnoreCase("C") && documentNumber.equals("23445322")) {
                return new Clients("Carlos", "Andrés", "Pérez", "Gómez", "3001234567", "Cra 45 #123-45", "Bogotá");
            }
            return new ErrorService("Cliente no encontrado", "", myClassName, 404);
        } catch (Exception e) {
            return new ErrorService("Error inesperado en la lógica de negocio", e.getMessage(), myClassName, 500);
        }
    }
}
