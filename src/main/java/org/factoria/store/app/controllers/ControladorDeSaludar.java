package org.factoria.store.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorDeSaludar {
    @GetMapping("/api/hola")
    public String saludar() {
        return "Hola Javi!";
    }
}
