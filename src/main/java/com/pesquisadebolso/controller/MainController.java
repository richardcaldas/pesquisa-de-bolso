package com.pesquisadebolso.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping(value="/")
    public ResponseEntity<String> getMain() {
        return ResponseEntity.ok("PESQUISA DE BOLSO AVAILABLE");
    }
    
}