package com.biblioteca.Biblioteca.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String checkStatus() {
        return "Servicio BIBLIOTECA OK. Controllers cargados.";
    }
}